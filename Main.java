package com.hellowworld;

import java.util.List;

public class Main {
    public static void main (String[]args){

        Datasource datasource=new Datasource();
        if(!datasource.open()){
            System.out.println("can't " +
                    "open datasource");
            return;
        }
         List<Artist> artists=datasource.queryArtists(Datasource.ORDER_BY_ASC);
        if(artists==null){
            System.out.println("No artist !");
            return;
        }
        for(Artist artist :artists){
            System.out.println("ID = "+artist.getId()+", Name = "+artist.getName());
        }

        List<String> albumsForArtists1=
                Datasource.queryAlbumsForArtist("Carole King",Datasource.ORDER_BY_ASC);
        for(String album: albumsForArtists1){
            System.out.println(album);
        }

        List<SongArtist>SongArtists = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if(SongArtists==null){
            System.out.println("couldn't find the artists for the song");
            return;
        }for(SongArtist artist:SongArtists){
            System.out.println("Artist name ="+ artist.getArtistName() + " Album name = "+ artist.getAlbumName()+
                    " Track =" +artist.getTrack());
        }
        datasource.QUERY_SONG_META_DATA();

         int count= datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("number of songs is: "+count);
         datasource.createViewForSongArtists();
         datasource.close();
    }

}
