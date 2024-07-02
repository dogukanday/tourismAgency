
# Turizm Acente Yönetim Paneli

Bu proje Turizm acentelerinin işlerini organize etmek ve kolayca yönetmek için oluşturulmuş bir paneldir.




## Yapılabilenler :

- Kullanıcı oluşturmak ve çalışanlarınıza ait özel hesaplar için kullanıcı yönetim sistemi.
- Otel Listeleme / ekleme / düzenleme / silme
- Oda Listeleme / ekleme / düzenleme / silme
- Pansiyon Listeleme / ekleme / düzenleme / silme
- Dönem Listeleme / ekleme / düzenleme / silme
- Rezervasyon Listeleme / ekleme / düzenleme / silme
- Müsait tarihlere göre oda arama


## Projeyi çalıştırmak için :

Terminalinizi açın ve dosyanın yükleneceği konumu seçin

```bash
  cd desktop
```

Projeyi klonlayın

```bash
  git clone https://github.com/dogukanday/tourismAgency
```



## Proje Yapısı :

    Business klasörü databaseden gelen verilerin yönetildiği alandır.

    Core klasörü DB bağlantısının yapıldığı ve diğer yardımcı işlemlerin tanımlandığı klasördür

    Dao klasörü DB'den veri çekme işlemlerinin yapıldığı alandır.

    Entities klasörü varlıkların üzerinde işlem yapılmasını sağlayan alandır.

    View klasörü UI verilerinin tasarlandığı ve baz işlemlerin yapıldığı alandır.