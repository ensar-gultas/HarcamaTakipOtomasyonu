import java.time.LocalDate;

public class DigerHarcamalar extends Harcama{

    DigerHarcamalar(double tutar, String aciklama, LocalDate tarih){
        super(tutar, aciklama, tarih);
    }

    @Override
    String kategori(){
        return "Diğer";
    }
}
