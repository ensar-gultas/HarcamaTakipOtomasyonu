import java.time.LocalDate;

public class UlasimHarcamasi extends Harcama{

    UlasimHarcamasi(double tutar, String aciklama, LocalDate tarih){
        super(tutar, aciklama, tarih);
    }

    @Override
    String kategori(){
        return "Ulaşım";
    }
}
