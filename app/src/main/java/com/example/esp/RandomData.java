package com.example.esp;

import android.app.VoiceInteractor;

import java.util.ArrayList;
import java.util.Random;

public class RandomData {

    public static ArrayList<Primary> images;

    public static ArrayList<Primary> getPrimaryImages(){
        images = new ArrayList<>();
        images.add(new Primary(false,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRU5Gq0_9GI5eosAc5lEIsMQ_vIZtYQk6JXwtuaBhi9ubfEOytm", new Secondary("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRqRQ5RuF9y6H81hie2-p_yqDnWQ0n0dLGtvtiw8XTu9X5XRv7j","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRVVnhN7xju4QzuJiQ5mHKWfxKx2Qv1Nvfat6MVMuhu-OQxB0M5")));
        images.add(new Primary(false,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRVVnhN7xju4QzuJiQ5mHKWfxKx2Qv1Nvfat6MVMuhu-OQxB0M5", new Secondary("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT6f-kh--HmonVG_rF_CpOR6laa0DAzdaVeajWCKGjHmZCEg743","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTKbzh593HTTN9rYptVwpzA_PGxlBp_Zw2L6WylcWndv62r3XCh")));
        images.add(new Primary(false,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTPCSlxomvUnU7sgk-GHuSiyGXRvnHOD6uSk9x03EHyK4OAdODU", new Secondary("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQYe4FQCoMFEV0Oy206D51HZNbvi7KYFWvI0MNtr818fA1KkYLN","https://www.telegraph.co.uk/content/dam/Travel/2018/January/white-plane-sky.jpg?imwidth=450")));
        images.add(new Primary(false,"https://www.telegraph.co.uk/content/dam/Travel/2018/January/white-plane-sky.jpg?imwidth=450", new Secondary("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTRLLsfzVdFkF7nEkSvh57_Ihk2gtYYt4BwzszIelzA_j7K8wi2","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSbO-gESsp0XlXt8dg2s17e09Ro6PSOOMERHM3MxxcBrcq2RH1E")));
        images.add(new Primary(false,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSbO-gESsp0XlXt8dg2s17e09Ro6PSOOMERHM3MxxcBrcq2RH1E", new Secondary("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRMAMrbLA2Tar-6JEOoyMHIzPhovy_KrQ2E00t21s1kJSq5MyRJ","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRUrXuyc53CqaTuhmxfD2htll4ZXCQ-K__oz168GW7Iv0eYtiK9")));
        images.add(new Primary(false,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRUrXuyc53CqaTuhmxfD2htll4ZXCQ-K__oz168GW7Iv0eYtiK9", new Secondary("https://rukminim1.flixcart.com/image/352/352/jfvfjbk0/cupboard-almirah/c/6/d/particle-board-csi-72a-crystal-furnitech-wenge-original-imaf48ss9cyhwavz.jpeg?q=70","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQcqvWm9VQBAtjW5JPDQjHhtOhmvAK0mns_zdPxAhYgLnwB8LQ2")));
        images.add(new Primary(false,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQcqvWm9VQBAtjW5JPDQjHhtOhmvAK0mns_zdPxAhYgLnwB8LQ2", new Secondary("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRGorecbdvx05qeBN4pgaLD1ZUi86ye7tK0a0WsDfnnOnyNM9Sa","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTDx72Z47hjJi9cSdZGhrJIF4VgYyN0dhyOq3suk5UNI4uOFIV7")));
        images.add(new Primary(false,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRWqA5OJHHXYEIkwOrCYVwOzeQC9DbOcxL3WqoQLn5_o38oQY_z", new Secondary("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRgf9N9n8RLQtYOsn8GwktRKCM1c3kQeILKMg2BRkqQxQDBQM6c","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS3z0fySKZ8jJAqgHkk4WUu6JD_rjYul72I0iDObiiSh3dhjYsy")));
        images.add(new Primary(false,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS4-4LjSx4klNftulS2bEwZ6ct4BwBIma8CFfHIzyGvuzzc3p0j", new Secondary("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRAmj-Oni5A31EPXwGYZJH8RgS_UGxGtv7XMR9avzuvJK2gKu14","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjxpn8LuEUsoaiK3GusP5XU6TlRKFP07-46XS3u1f9bcHg3WsS")));
        return images;
    }

    public static String getImage(int index){
        return images.get(index).question;
    }

    public static int getRandomImageURL(ArrayList<Primary> images){
        Random random = new Random();
        return random.nextInt(images.size());
    }

}
