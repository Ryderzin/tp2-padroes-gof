"""Monta entrega/entrega.html a partir de modelo.html, inserindo o código-fonte dos projetos
com cores no estilo do tema Dark+ do Visual Studio Code.

Uso: python entrega/gerar.py [link-do-repositorio]
Depois, gere o PDF imprimindo o HTML (ex.: Microsoft Edge em modo headless).
"""
import html
import pathlib
import re
import sys

ENTREGA = pathlib.Path(__file__).resolve().parent
RAIZ = ENTREGA.parent
repo = sys.argv[1] if len(sys.argv) > 1 else "https://github.com/Ryderzin/tp2-padroes-gof"

# Palavras-chave de declaração (azul) e de controle de fluxo (rosa), como no Dark+.
DECLARACAO = {
    "package", "import", "public", "private", "protected", "static", "final", "abstract",
    "class", "interface", "extends", "implements", "new", "this", "super", "void",
    "double", "int", "long", "float", "boolean", "char", "byte", "short", "true", "false", "null",
}
CONTROLE = {"return", "for", "while", "if", "else", "switch", "case", "break", "continue", "try", "catch", "throw"}

TOKEN = re.compile(
    r"(?P<javadoc>/\*\*[\s\S]*?\*/)"
    r"|(?P<comentario>/\*[\s\S]*?\*/|//[^\n]*)"
    r"|(?P<texto>\"(?:\\.|[^\"\\])*\")"
    r"|(?P<anotacao>@[A-Za-z_]\w*)"
    r"|(?P<numero>\b\d+(?:\.\d+)?\b)"
    r"|(?P<palavra>[A-Za-z_]\w*)"
    r"|(?P<outro>[\s\S])"
)


def span(classe: str, texto: str) -> str:
    return f'<span class="{classe}">{html.escape(texto)}</span>'


def colorir_javadoc(texto: str) -> str:
    partes = re.split(r"(@\w+)", texto)
    return "".join(span("kw", p) if p.startswith("@") else span("cm", p) for p in partes if p)


def colorir(fonte: str) -> str:
    saida = []
    tokens = list(TOKEN.finditer(fonte))
    for i, m in enumerate(tokens):
        tipo, valor = m.lastgroup, m.group()
        if tipo == "javadoc":
            saida.append(colorir_javadoc(valor))
        elif tipo == "comentario":
            saida.append(span("cm", valor))
        elif tipo == "texto":
            saida.append(span("st", valor))
        elif tipo == "anotacao":
            saida.append(span("an", valor))
        elif tipo == "numero":
            saida.append(span("nu", valor))
        elif tipo == "palavra":
            proximo = fonte[m.end():].lstrip(" ")
            anterior = tokens[i - 2].group() if i >= 2 else ""
            if valor in CONTROLE:
                saida.append(span("ct", valor))
            elif valor in DECLARACAO:
                saida.append(span("kw", valor))
            elif anterior in ("package", "import") or fonte[max(0, m.start() - 1)] == "." and valor[0].islower() and not proximo.startswith("("):
                # nomes de pacote e acessos a campos
                saida.append(span("va", valor))
            elif valor[0].isupper():
                saida.append(span("ty", valor) if not valor.isupper() else span("va", valor))
            elif proximo.startswith("("):
                saida.append(span("fn", valor))
            else:
                saida.append(span("va", valor))
        else:
            saida.append(html.escape(valor))
    return "".join(saida)


def bloco(fonte: str) -> str:
    return f'<pre class="vscode">{colorir(fonte.rstrip())}</pre>'


def codigo(m: re.Match) -> str:
    caminho = m.group(1)
    fonte = (RAIZ / caminho).read_text(encoding="utf-8")
    nome = pathlib.Path(caminho).name
    return f'<div class="editor"><div class="aba">{nome}</div>{bloco(fonte)}</div>'


modelo = (ENTREGA / "modelo.html").read_text(encoding="utf-8")
saida = re.sub(r"\{\{CODE:([^}]+)\}\}", codigo, modelo)
# Trechos de código escritos direto no modelo: <pre class="java">...</pre>
saida = re.sub(
    r'<pre class="java">([\s\S]*?)</pre>',
    lambda m: bloco(html.unescape(m.group(1))),
    saida,
)
saida = saida.replace("{{REPO}}", html.escape(repo))
(ENTREGA / "entrega.html").write_text(saida, encoding="utf-8")
print("ok:", ENTREGA / "entrega.html")
