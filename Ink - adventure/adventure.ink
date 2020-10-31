# author: Artur Franco
# theme: dark

VAR tocha = 0
Iniciar -> casa

=== casa ===
Minha casa é uma maravilha
+   Ir para pantâno -> pantano
+   Ir para floresta -> floresta.clareira
+   {tocha == 1} Ir para caverna -> caverna

=== pantano ===
Pântano fétido

+   Ir para floresta -> floresta.clareira
+   Ir para casa de veia -> floresta.casa_da_veia

=== floresta ===
= casa_da_veia
Aqui é a casa da véia
~ tocha = 1
+   Voltar para casa -> casa

= clareira
Clareia muito estranha
+   Ir para casa -> casa
+   Pegar no pau da véia  -> casa_da_veia
+   Ir para cidade -> cidade

=== caverna ===
Caverna cheia de bosta e mortal, você nem sabe que bicho te mordeu!
-> END

=== cidade ===
Cidade cheio de cobradores!
Melhor voltar para casa -> casa

-> END