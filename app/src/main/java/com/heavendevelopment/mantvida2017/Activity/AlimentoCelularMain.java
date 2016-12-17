package com.heavendevelopment.mantvida2017.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heavendevelopment.mantvida2017.Dominio.AlimentoCelular;
import com.heavendevelopment.mantvida2017.R;

public class AlimentoCelularMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimento_celular_main);

        String versículo = "“Quando passares pelas águas estarei contigo, e quando pelos rios, eles não te submergirão; quando passares pelo fogo, não te queimarás, nem a chama arderá em ti”. (Isaías 43.2)";
        String texto = "É muito reconfortante para nós sabermos que temos uma presença diuturnamente conosco, que não dorme e nem cochila. E além dessa presença grandiosa, maravilhosa, magnífica, poder contar com o Seu poder agindo ao nosso favor. Isso verdadeiramente nos traz paz, conforto, alegria e a firme convicção que venceremos todos os níveis de desafios.\n" +
                "Esse é o propósito que Deus tem para o homem, cuidar e proteger aqui na Terra e na eternidade, para sempre ao Seu Lado.\n" +
                "Mas para desfrutarmos do conforto de sua presença e da segurança infalível capaz de vencer qualquer tentativa contra a nossa vida, se faz necessário permanecer em Sua Palavra.\n" +
                "“Se vós estiverdes em mim, e as minhas palavras estiverem em vós, pedireis tudo o que quiserdes, e vos será feito”. (João 15.7 )\n" +
                "Para andar com Deus somente uma coisa é necessária: permanecer na Palavra, isto é, fazer de acordo com o que o Mestre Jesus determinou. Muito simples, mas para muitos, complexo, em razão de estar sempre procurando viver pelo seu modo de pensar e agir.\n" +
                "O fato de andarmos com Deus nos leva a gozar da companhia divina e da infalível segurança do Eterno. Contudo, há muitas pessoas que entendem de uma maneira mais complexa o que é andar com Deus. E por entender de uma maneira complexa, procuram se mutilar, outros vão atrás do famoso ouvir Deus por meio de profecias, outros não se firmam na igreja local, pois dizem que querem mais da presença do Senhor.  Contudo, não conseguem perceber que: permanecer em obediência, ou seja, em observação a tudo o que está escrito na Bíblia, com o desejo de trazer para o seu dia a dia, pode gozar com toda a certeza da presença divina. Pois a Bíblia é a Palavra do Eterno, materializada na Terra.\n" +
                "“Se guardardes os meus mandamentos, permanecereis no meu amor; do mesmo modo que eu tenho guardado os mandamentos de meu Pai, e permaneço no seu amor. Tenho-vos dito isto, para que o meu gozo permaneça em vós, e o vosso gozo seja completo”. (João 15:10-11)\n" +
                "No livro de João o capítulo 1 e versículos 1-4 e 10 a Bíblia diz que Jesus habitou na terra juntamente com os homens. A partir desse momento histórico em que o filho de Deus, a própria Palavra, se materializa e agora pode ser companhia física do homem, podemos sim ter a sua presença em todos os momentos. Mas talvez você agora está conjecturando em seu coração: “Ele não ressuscitou e voltou para o Pai?” Sim! Contudo, não nos deixou órfãos.\n" +
                "“E eu rogarei ao Pai, e ele vos dará outro Consolador, para que fique convosco para sempre; Espírito de verdade, que o mundo não pode receber, porque não o vê nem o conhece; mas vós o conheceis, porque habita convosco, e estará em vós”. (João 14:16-17)\n" +
                "A companhia do Eterno estará sempre comigo se eu desejar permanecer e fizer conforme o que a Bíblia orienta.\n" +
                "“Aquele que tem os meus mandamentos e os guarda esse é o que me ama; e aquele que me ama será amado de meu Pai, e eu o amarei, e me manifestarei a ele”. (João 14:21)\n" +
                "Essa compreensão da presença divina em nosso dia a dia faz toda a diferença emocional, espiritual e física. Porque se estamos ligados a Palavra não apenas em nosso intelecto, como fonte de conhecimento, mas como a presença do Eterno conosco se materializando em cada passo de obediência em que fizermos, certamente veremos a Glória de Deus agindo em nosso favor, de uma maneira sobrenatural, porque o mundo não pode vê-lo por não querer obedecer a seus estatutos.\n" +
                "E por obedecer, fazer conforme, em amor e reverência por ser a Palavra do Eterno, podemos então ouvir Deus dizer:\n" +
                "“Quando passares pelas águas estarei contigo, e quando pelos rios, eles não te submergirão; quando passares pelo fogo, não te queimarás, nem a chama arderá em ti”. (Isaías 43.2)\n" +
                "Ande com Deus, não crie atalhos.";

        AlimentoCelular alimentoCelular = new AlimentoCelular("Sempre Comigo","05/12/2016",versículo,texto, null);
    }
}
