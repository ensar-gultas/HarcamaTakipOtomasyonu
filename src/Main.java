import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int suankiYil = LocalDate.now().getYear();
    static int minYil = LocalDate.now().minusYears(1).getYear();

    public static void main(String[] args) {

        Harcama harcama;
        HarcamaYonetimi harcamaYonetimi = new HarcamaYonetimi();

        boolean cikisYapildiMi = false;

        while (!cikisYapildiMi){
            System.out.println("\n---HARCAMA TAKİP OTOMASYONU---");
            System.out.println("1- Harcama Ekle");
            System.out.println("2- Harcama Geçmişini Görüntüle");
            System.out.println("3- Dönemsel Harcama Raporları (Haftalık, Aylık ve Yıllık)");
            System.out.println("4- Kategori Bazlı Analiz ve Dağılım");
            System.out.println("5- Özel Tarih Aralığı Sorgula");
            System.out.println("6- Çıkış");

            System.out.print("\nLütfen yapmak istediğiniz işlemin numarasını giriniz: ");
            int secim = scanner.nextInt();

            switch (secim){
                case 1:
                    System.out.println("\n---KATEGORİLER---");
                    System.out.println("1- Gıda Harcaması");
                    System.out.println("2- Konut Harcaması");
                    System.out.println("3- Ulaşım Harcaması");
                    System.out.println("4- Diğer Harcamalar");
                    System.out.print("Harcama eklemek istediğiniz kategoriyi seçiniz: ");
                    int kategori = scanner.nextInt();

                    while (kategori < 1 || kategori > 4){
                        System.out.println("Hata! Kategori seçimi 1-4 sayıları arasında olmalıdır!");
                        System.out.print("\nKategori seçiminizi tekrar giriniz: ");
                        kategori = scanner.nextInt();
                    }

                    System.out.println("\n---Harcama Bilgileri---");

                    System.out.print("\nHarcama tutarını giriniz: ");
                    double tutar = scanner.nextDouble();
                    while (tutar <= 0){
                        System.out.println("\nHarcama tutarı 0 veya daha az olamaz!");
                        System.out.print("\nHarcama tutarını tekrar giriniz: ");
                        tutar = scanner.nextDouble();
                    }

                    System.out.print("Harcamanın yapıldığı günü giriniz (1-31): ");
                    int gun = scanner.nextInt();
                    System.out.print("Harcamanın yapıldığı ayı giriniz (1-12): ");
                    int ay = scanner.nextInt();
                    System.out.print("Harcamanın yapıldığı yılı giriniz (" + minYil +" - " + suankiYil + "): ");
                    int yil = scanner.nextInt();
                    LocalDate tarih = tarihKontrol(gun, ay, yil);
                    scanner.nextLine();

                    System.out.print("Harcama için kısa bir açıklama yazınız: ");
                    String aciklama = scanner.nextLine();
                    while (aciklama.isEmpty()){
                        System.out.println("\nAçıklama boş bırakılamaz!");
                        System.out.print("\nAçıklamayı giriniz: ");
                        aciklama = scanner.nextLine();
                    }

                    if(kategori == 1){
                        harcama = new GidaHarcamasi(tutar, aciklama, tarih);
                    }
                    else if(kategori == 2){
                        harcama = new KonutHarcamasi(tutar, aciklama, tarih);
                    }
                    else if(kategori == 3){
                        harcama = new UlasimHarcamasi(tutar, aciklama, tarih);
                    }
                    else{
                        harcama = new DigerHarcamalar(tutar, aciklama, tarih);
                    }
                    harcamaYonetimi.harcamaEkle(harcama);
                    break;

                case 2:
                    System.out.println("\n---TÜM HARCAMA GEÇMİŞİNİZ---");
                    harcamaYonetimi.harcamalariListele();
                    System.out.println("Toplam Harcama: " + harcamaYonetimi.toplamHarcama() + " TL");
                    break;

                case 3:
                    System.out.println("\n---DÖNEMSEL HARCAMA RAPORLARI---");
                    System.out.println("1- Haftalık Rapor");
                    System.out.println("2- Aylık Rapor (1, 3 ve 6 Aylık)");
                    System.out.println("3- Yıllık Rapor");
                    System.out.print("\nGörüntülemek istediğiniz rapor dönemini seçiniz: ");
                    int raporSecim = scanner.nextInt();

                    while (raporSecim <= 0 || raporSecim > 3){
                        System.out.println("\nHata! Rapor seçim değeri 1-3 arasında olmalıdır!");
                        System.out.print("\nRapor dönemini tekrar seçiniz: ");
                        raporSecim = scanner.nextInt();
                    }

                    if(raporSecim == 1){
                        System.out.println("1 Haftalık Harcama Raporu: " + harcamaYonetimi.birHaftalikToplamHarcama() + " TL");
                    }
                    else if(raporSecim == 2){
                        System.out.print("Lütfen aylık rapor süresini seçiniz (1, 3 veya 6 ay): ");
                        int aylikSecim = scanner.nextInt();

                        while(aylikSecim != 1 && aylikSecim != 3 && aylikSecim != 6){
                            System.out.println("\nHata! Aylık rapor süresi 1, 3 veya 6 olmalıdır!");
                            System.out.print("\nAylık rapor süresini tekrar seçiniz: ");
                            aylikSecim = scanner.nextInt();
                        }
                        System.out.println(aylikSecim + " Aylık Harcama Raporu: " + harcamaYonetimi.aylikToplamHarcama(aylikSecim) + " TL");
                    }
                    else{
                        System.out.println("1 Yıllık Harcama Raporu: " + harcamaYonetimi.aylikToplamHarcama(12) + " TL");
                    }
                    break;

                case 4:
                    System.out.println("\n--- KATEGORİSEL HARCAMA RAPORU ---");
                    System.out.println("1- Kategori Bazlı Toplam Harcama");
                    System.out.println("2- Kategori Harcamalarının Yüzdesel Dağılımı");
                    System.out.print("\nLütfen yapmak istediğiniz işlemi seçiniz: ");
                    int secenek = scanner.nextInt();

                    while (secenek < 1 || secenek > 2){
                        System.out.println("\nHata! Seçiminiz 1 veya 2 olmalıdır!");
                        System.out.print("\nİşlem seçimininizi tekrar giriniz: ");
                        secenek = scanner.nextInt();
                    }
                    scanner.nextLine();

                    if(secenek == 1){
                        String [] kategoriler = {"Gıda", "Konut", "Ulaşım", "Diğer"};
                        boolean kategoriBulundu;

                        do {
                            System.out.print("\nRaporunu incelemek istediğiniz kategoriyi yazınız (Gıda, Konut, Ulaşım, Diğer): ");
                            String kategoriSecim = scanner.nextLine();

                            kategoriBulundu = false;

                            for (String kategoriAdi : kategoriler){
                                if(kategoriAdi.equalsIgnoreCase(kategoriSecim)){
                                    System.out.println(kategoriAdi +" Kategorisinin Harcama Raporu: " + harcamaYonetimi.kategoriyeGoreToplam(kategoriAdi) + " TL");
                                    kategoriBulundu = true;
                                    break;
                                }
                            }
                            if (!kategoriBulundu) {
                                System.out.println("Geçersiz kategori girdiniz! Tekrar deneyin.");
                            }
                        }while (!kategoriBulundu);
                    }
                    else{
                        System.out.println("\n--- HARCAMALARIN YÜZDESEL DAĞILIMI ---\n");
                        harcamaYonetimi.kategoriYuzdeleriniGoster();
                    }
                    break;

                case 5:
                    System.out.println("\n--- TARİH ARALIĞI HARCAMA RAPORU ---");

                    while (true){
                        
                        System.out.println("\n---Başlangıç tarihinin gün, ay ve yıl değerlerini giriniz---");
                        System.out.print("Gün (1-31): ");
                        int baslangicGun = scanner.nextInt();
                        System.out.print("Ay (1-12): ");
                        int baslangicAy = scanner.nextInt();
                        System.out.print("Yıl ("+ minYil + " - " + suankiYil + "): ");
                        int baslangicYil = scanner.nextInt();
                        LocalDate baslangicTarihi = tarihKontrol(baslangicGun, baslangicAy, baslangicYil);
                        
                        
                        System.out.println("\n---Bitiş tarihinin gün, ay ve yıl değerlerini giriniz---");
                        System.out.print("Gün (1-31): ");
                        int bitisGun = scanner.nextInt();
                        System.out.print("Ay (1-12): ");
                        int bitisAy = scanner.nextInt();
                        System.out.print("Yıl ("+ minYil + " - " + suankiYil + "): ");
                        int bitisYil = scanner.nextInt();
                        LocalDate bitisTarihi = tarihKontrol(bitisGun, bitisAy, bitisYil);

                        if(baslangicTarihi.isBefore(bitisTarihi) || baslangicTarihi.isEqual(bitisTarihi)){
                            System.out.println("Seçilen tarihler arası toplam harcama: " + harcamaYonetimi.tarihAraligiToplamHarcama(baslangicTarihi, bitisTarihi) + " TL");
                            break;
                        }
                        else{
                            System.out.println("Hata! Başlangıç tarihi bitiş tarihinden sonra olamaz!");
                        }
                    }
                    break;

                case 6:
                    System.out.println("\nProgramdan çıkılıyor. İyi günler!");
                    cikisYapildiMi = true;
                    break;

                default:System.out.println("Geçersiz seçim! Lütfen menüden 1-6 arasında bir sayı giriniz.");
                break;
            }
        }
    }
    
    static LocalDate tarihKontrol(int gun, int ay, int yil){

        while (gun <= 0 || gun > 31) {
            System.out.println("\nGün değeri 1-31 arasında olmalıdır!");
            System.out.print("Günü tekrar giriniz: ");
            gun = scanner.nextInt();
        }

        while (ay <= 0 || ay > 12) {
            System.out.println("\nAy değeri 1-12 arasında olmalıdır!");
            System.out.print("Ayı tekrar giriniz: ");
            ay = scanner.nextInt();
        }

        while (yil < minYil || yil > suankiYil) {
            System.out.println("\nYıl " + minYil +" veya " + suankiYil + " olmalıdır!");
            System.out.print("Yılı tekrar giriniz: ");
            yil = scanner.nextInt();
        }
        return LocalDate.of(yil, ay, gun);
    }
}
