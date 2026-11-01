"""Mutação manual da linha `description` de PokemonUIMapper.kt.

Uso: python mutacao_desc.py <raiz-do-PokedexCompose>
Roda um controle (código original, deve passar), aplica cada mutante, roda
PokemonUIMapperTest e restaura o original. Só conta como MORTO quando o
Gradle relata teste falhando; qualquer outra falha é ERRO (não é evidência).
"""
import pathlib
import re
import subprocess
import sys

raiz = pathlib.Path(sys.argv[1]).resolve()
gradlew = str(raiz / "gradlew.bat")
arq = raiz / "app/src/main/java/com/example/pokedexcompose/ui/mappers/PokemonUIMapper.kt"
ORIGINAL = r'this.description.replace(Regex("\\s+"), " ").trim()'
MUTANTES = {
    "M0 controle (sem mutacao)": ORIGINAL,
    "M1 codigo antigo (so \\n)": r'this.description.replace("\n", " ")',
    "M2 sem trim": r'this.description.replace(Regex("\\s+"), " ")',
    "M3 \\s sem +": r'this.description.replace(Regex("\\s"), " ").trim()',
    "M4 so \\n e \\f": r'this.description.replace(Regex("[\n\u000c]+"), " ").trim()',
    "M5 so trim": r'this.description.trim()',
}


def rodar():
    r = subprocess.run(
        ["cmd", "/c", gradlew, ":app:testDebugUnitTest",
         "--tests", "*PokemonUIMapperTest*", "--console=plain"],
        cwd=raiz, capture_output=True, text=True, encoding="utf-8", errors="replace",
    )
    saida = r.stdout + r.stderr
    falhas = re.search(r"(\d+) tests completed, (\d+) failed", saida)
    if r.returncode == 0 and "BUILD SUCCESSFUL" in saida:
        return "PASSOU", ""
    if falhas:
        nomes = re.findall(r"PokemonUIMapperTest > (.+?) FAILED", saida)
        return "FALHOU", f"{falhas.group(0)}: " + "; ".join(nomes)
    return "ERRO", ascii(saida[-300:])


texto = arq.read_text(encoding="utf-8")
assert texto.count(ORIGINAL) == 1, "trecho original nao encontrado"
try:
    for nome, mut in MUTANTES.items():
        arq.write_text(texto.replace(ORIGINAL, mut), encoding="utf-8", newline="")
        status, detalhe = rodar()
        if nome.startswith("M0"):
            veredito = "OK" if status == "PASSOU" else "CONTROLE QUEBRADO"
        else:
            veredito = {"FALHOU": "MORTO", "PASSOU": "SOBREVIVEU"}.get(status, "ERRO")
        print(f"{nome:28} -> {veredito} {detalhe}")
finally:
    arq.write_text(texto, encoding="utf-8", newline="")
