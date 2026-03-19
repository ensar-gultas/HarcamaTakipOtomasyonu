import java.time.LocalDate;

public class KonutHarcamasi extends Harcama{

    KonutHarcamasi(double tutar, String aciklama, LocalDate tarih){
        super(tutar, aciklama, tarih);
    }

    @Override
    String kategori(){
        return "Konut";
    }
}
