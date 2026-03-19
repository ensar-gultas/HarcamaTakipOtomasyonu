import java.time.LocalDate;
import java.util.ArrayList;

public class HarcamaYonetimi {

    static ArrayList<Harcama> harcamalar = new ArrayList<>();


    public void harcamaEkle(Harcama yeniHarcama){
        harcamalar.add(yeniHarcama);
    }

    public void harcamalariListele(){
        if(harcamalar.isEmpty()){
            System.out.println("Henüz kayıtlı bir harcama kaydı yok.");
            return;
        }

        for (int i = 0; i < harcamalar.size(); i++) {
            for (int j = i+1; j < harcamalar.size(); j++) {
                if(harcamalar.get(i).getTarih() != null && harcamalar.get(i).getTarih().isBefore(harcamalar.get(j).getTarih())){
                    Harcama temp = harcamalar.get(i);
                    harcamalar.set(i, harcamalar.get(j));
                    harcamalar.set(j, temp);
                }
            }
        }
        for (Harcama harcama : harcamalar){
            System.out.println(harcama);
        }
    }

    public double toplamHarcama(){
        double toplam = 0;

        if(harcamalar.isEmpty()){
            return toplam;
        }

        for (Harcama harcama : harcamalar){
            toplam += harcama.getTutar();
        }
        return toplam;
    }

    public double kategoriyeGoreToplam(String kategori){
        double kategoriToplam = 0;

        if(harcamalar.isEmpty()){
            return kategoriToplam;
        }

        for (Harcama harcama : harcamalar){
            if(harcama.kategori().equals(kategori)){
                System.out.println(harcama);
                kategoriToplam += harcama.getTutar();
            }
        }
        return kategoriToplam;
    }

    public void kategoriYuzdeleriniGoster(){

        if(harcamalar.isEmpty()){
            System.out.println("Henüz kayıtlı bir harcama kaydı yok.");
        }
        else{
            String [] kategoriler = {"Gıda", "Ulaşım", "Konut", "Diğer"};
            double genelToplam = toplamHarcama();

            for (String kategori : kategoriler){
                double kategoriToplam = 0;
                for(Harcama h : harcamalar){
                    if(h.kategori().equals(kategori)){
                        kategoriToplam += h.getTutar();
                    }
                }
                if(genelToplam > 0){
                    double yuzde = (kategoriToplam / genelToplam) * 100;
                    System.out.printf("%s Oranı: %.2f%%\n", kategori, yuzde);
                }
                else{
                    System.out.println("Henüz harcama kaydı yok.");
                }
            }
        }
    }

    public double birHaftalikToplamHarcama(){
        double haftalikToplam = 0;

        if(harcamalar.isEmpty()){
            return haftalikToplam;
        }
        else{
            LocalDate bugun = LocalDate.now();
            LocalDate birHaftaOnce = LocalDate.now().minusWeeks(1);

            for (Harcama harcama : harcamalar){
                if( (harcama.getTarih().isAfter(birHaftaOnce) || harcama.getTarih().isEqual(birHaftaOnce)) &&
                    (harcama.getTarih().isBefore(bugun) || harcama.getTarih().isEqual(bugun)) ){
                    System.out.println(harcama);
                    haftalikToplam += harcama.getTutar();
                }
            }
            return haftalikToplam;
        }
    }

    public double aylikToplamHarcama(int ay){
        double aylikToplam = 0;

        if(harcamalar.isEmpty()){
            return aylikToplam;
        }
        else if(ay == 1 || ay == 3 || ay == 6 || ay == 12){
            LocalDate bugun = LocalDate.now();
            LocalDate ayOnce = LocalDate.now().minusMonths(ay);

            for (Harcama harcama : harcamalar){
                if( (harcama.getTarih().isAfter(ayOnce) || harcama.getTarih().isEqual(ayOnce)) &&
                    (harcama.getTarih().isBefore(bugun) || harcama.getTarih().isEqual(bugun)) ){
                    System.out.println(harcama);
                    aylikToplam += harcama.getTutar();
                }
            }
        }
        else{
            System.out.println("Sadece 1, 3, 6 veya 12 aylık harcama tutarı raporu alabilirsiniz!");
        }
        return aylikToplam;
    }

    public double tarihAraligiToplamHarcama(LocalDate baslangic, LocalDate bitis){
        double tarihAraligiToplam = 0;
        LocalDate bugun = LocalDate.now();

        if(harcamalar.isEmpty()){
            return tarihAraligiToplam;
        }
        else if(baslangic.isAfter(bugun) || bitis.isAfter(bugun)){
            System.out.println("Uyarı! Geçerli tarih aralığı giriniz!");
        }
        else if(baslangic.isAfter(bitis)){
            System.out.println("Başlangıç tarihi bitiş tarihinden sonra olamaz!");
        }
        else{
            for (Harcama harcama : harcamalar){
                if( (harcama.getTarih().isAfter(baslangic) || harcama.getTarih().isEqual(baslangic)) &&
                    (harcama.getTarih().isBefore(bitis) || harcama.getTarih().isEqual(bitis)) ){
                    System.out.println(harcama);
                    tarihAraligiToplam += harcama.getTutar();
                }
            }
        }
        return tarihAraligiToplam;
    }


}
