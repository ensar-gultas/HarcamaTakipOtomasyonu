import java.time.LocalDate;

public class GidaHarcamasi extends Harcama{

    GidaHarcamasi(double tutar, String aciklama, LocalDate tarih){
        super(tutar, aciklama, tarih);
    }

    @Override
    String kategori(){
        return "Gıda";
    }
}
