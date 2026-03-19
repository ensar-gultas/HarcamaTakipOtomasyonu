# 💰 Harcama Takip Otomasyonu (Expense Tracking System)

Bu proje, kişisel harcamaların kaydedilmesi, kategorize edilmesi ve çeşitli filtreleme yöntemleriyle raporlanmasını sağlayan Java tabanlı bir takip sistemidir. Uygulama, temel programlama mantığı ve Nesne Yönelimli Programlama (OOP) yapısı kullanılarak geliştirilmiştir.

## 🚀 Projenin Amacı ve Teknik Kazanımlar
Bu otomasyon, bir finansal takip sisteminin temel işleyişini simüle eder. Proje geliştirilirken özellikle **veri doğrulama (Validation)**, **kullanıcı girişlerinin kontrolü** ve **dinamik raporlama algoritmaları** üzerine çalışılmıştır.

## ⚙️ Temel Özellikler (Features)

* **💳 Harcama Kaydı Oluşturma:** Gıda, Konut, Ulaşım ve Diğer kategorilerinde; tutar, tarih ve açıklama kontrolleri yapılarak sisteme veri girişi sağlanması.
* **📅 Dinamik Tarih Filtreleme:** `java.time.LocalDate` kullanılarak, içinde bulunulan yıldan bir önceki yılın başından itibaren (yaklaşık 2 yıllık bir pencerede) veri girişine izin veren kontrol mekanizması.
* **📊 Dönemsel Özet Raporlar:** 1 Haftalık, 1-3-6 Aylık ve Yıllık periyotlarda toplam harcama tutarlarının hesaplanması.
* **📈 Kategori Bazlı Analiz:** Harcamaların kategorilere göre gruplandırılması ve genel bütçe içindeki yüzdesel (%) dağılımının raporlanması.
* **🔍 Özel Tarih Aralığı Sorgulama:** Kullanıcının belirlediği iki tarih arasında (başlangıç/bitiş kıyaslaması yapılarak) detaylı harcama listeleme.
* **🗂️ Kronolojik Sıralama:** Harcama geçmişinin, tarihlere göre "en yeni en üstte" olacak şekilde özel bir sıralama mantığıyla ekrana getirilmesi.

## 🛠️ Kullanılan Teknolojiler ve Kavramlar

* **Dil:** Java (JDK)
* **OOP Yapısı:** Inheritance (Kalıtım) ve Polymorphism (Çok Biçimlilik) prensipleri kullanılarak harcama tipleri yönetilmiştir.
* **Veri Yönetimi:** Harcamaların dinamik olarak tutulması için `ArrayList` koleksiyon yapısı tercih edilmiştir.
* **Veri Doğrulama:** Kullanıcıdan alınan gün, ay ve yıl bilgilerinin doğruluğunu denetleyen yardımcı metotlar (örn: `tarihKontrol`) kullanılmıştır.

## 📝 Gelecek Planları (Roadmap)
Uygulama şu an verileri geçici bellek (RAM) üzerinde tutmaktadır. Projenin bir sonraki aşamasında:
* Girilen verilerin bir `.txt` dosyasına kaydedilmesi ve uygulama başlatıldığında bu dosyadan verilerin geri yüklenmesi planlanmaktadır.
