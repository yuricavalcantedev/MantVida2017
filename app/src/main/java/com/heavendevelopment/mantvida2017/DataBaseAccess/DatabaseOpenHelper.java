package com.heavendevelopment.mantvida2017.DataBaseAccess;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.heavendevelopment.mantvida2017.Activity.VisualizarEventoActivity;
import com.heavendevelopment.mantvida2017.R;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.GregorianCalendar;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by Alysson on 17/11/2015.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    private static final String BD_NAME = "db_mantvida_2017";
    private static final int BD_VERSION = 5;
    private Context context;

    public DatabaseOpenHelper(Context ctx) {
        super(ctx, BD_NAME, null, BD_VERSION);
        context = ctx;
    }

    public DatabaseOpenHelper(Context ctx, String BD) {
        super(ctx, BD, null, BD_VERSION);
        context = ctx;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        switch(newVersion) {

            case 2:

                ContentValues contentValues = new ContentValues();
                String descricao = context.getResources().getString(R.string.descricao_evento_intimidade);

                contentValues.put("id", 32);
                contentValues.put("nome", "Intimidade");
                contentValues.put("data", "13.01.17");
                contentValues.put("descricao", descricao);

                db.insert("evento",null,contentValues);

                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                int diaHoje = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
                int mesHoje = gregorianCalendar.get(GregorianCalendar.MONTH) + 1;

                if(mesHoje == 1 && diaHoje == 12 || mesHoje == 1 && diaHoje == 13) {
                    gerarNotificacaoIntimidade(descricao);
                }

                break;

            case 5:
                String descricao2 = context.getResources().getString(R.string.descricao_evento_intimidade);
                gerarNotificacaoIntimidade(descricao2);

        }
    }

    private void gerarNotificacaoIntimidade(String descricao){

        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Bundle bundle = new Bundle();
        bundle.putString("tituloEvento","Intimidade");
        bundle.putString("dataEvento", "13.01.17");
        bundle.putString("descricaoEvento", descricao);
        bundle.putString("situacaoEvento", "");

        Intent intentVer= new Intent(context, VisualizarEventoActivity.class);
        intentVer.putExtras(bundle);


        intentVer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent piVerDescricao = PendingIntent.getActivity(context,0,intentVer,PendingIntent.FLAG_CANCEL_CURRENT);

        android.support.v7.app.NotificationCompat.InboxStyle style = new android.support.v7.app.NotificationCompat.InboxStyle();
        style.addLine("O intimidade já é amanhã (Sexta - 13).");
        style.addLine("Não perca, vamos buscá-Lo!");
        style.addLine("Clique para ver a descrição do evento.");


        Notification notification = new NotificationCompat.Builder(context)
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_MAX)
                .setSmallIcon(R.drawable.lg_icant22x22)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_intimidade_72_dp))
                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.ic_done_white_24dp, "Ver Evento",piVerDescricao)  // #1
                // Apply the media style template
                .setContentTitle("MantVida Evento - Intimidade")
                .setTicker("MantVida Evento - Intimidade")
                .setContentText("O intimidade já é amanhã (Sexta - 13).\n Não perca, vamos buscá-Lo! Clique para ver a descrição do evento.")
                .setStyle(style)
                .setSound(soundUri)
                .build();

        notification.vibrate = new long[]{150, 300, 150, 600};
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.lg_icant22x22, notification);

    }

    /**
     * @param db db of application
     * this method update the wrong data of table "tblleitura_1tri"
     */

}