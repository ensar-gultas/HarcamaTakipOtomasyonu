import java.time.LocalDate;

public abstract class Harcama {

    private double tutar;
    private String aciklama;
    private LocalDate tarih;

    Harcama(double tutar, String aciklama, LocalDate tarih){
        setTutar(tutar);
        setAciklama(aciklama);
        setTarih(tarih);
    }

    abstract String kategori();

    void setTutar(double tutar){
        if(tutar > 0){
            this.tutar = tutar;
        }
        else{
            System.out.println("Harcama tutarı 0 veya daha az olamaz!");
        }
    }
    double getTutar(){
        return tutar;
    }

    void setAciklama(String aciklama){
        if(aciklama != null && !aciklama.isEmpty()){
            this.aciklama = aciklama;
        }
        else{
            System.out.println("Açıklama boş bırakılamaz!");
        }
    }

    void setTarih(LocalDate tarih){
        if(tarih != null && !tarih.isAfter(LocalDate.now())){
            this.tarih = tarih;
        }
        else{
            System.out.println("Tarih şu anki tarihten sonra olamaz!");
        }
    }
    LocalDate getTarih(){
        return tarih;
    }

    public String toString(){
        return "\nKategori: " + kategori() + " \nTutar: " + tutar + " TL" + " \nTarih: " + tarih + " \nAçıklama: " + aciklama +
                "\n-------------------";
    }
}
