# Refleksi Tutorial Pemrograman Lanjut

<details>
<summary style="font-size:24px">Tutorial 1</summary>

## Clean Code
_Clean code_ merujuk pada suatu konsep dalam proses pengembangan perangkat lunak yang menitikberatkan pada pentingnya 
menulis kode yang mudah terbaca, dapat dipahami, dan dapat dijaga dengan baik oleh pengembang lainnya. Prinsip-prinsip
_clean code_ berperan dalam mengurangi tingkat kompleksitas, meningkatkan tingkat keterbacaan, serta mempermudah proses 
pemeliharaan kode.

Dalam tutorial modul 1 kali ini, beberapa praktik _clean code_ yang telah diterapkan seperti,

### 1. Meaningful Variable Names
Nama variabel seperti `product` dan `productId` dengan jelas menyampaikan makna dari variabel-variabel tersebut.

### 2. Function
Berikut contoh penggunaan function pada tutorial kali ini:
```java
...
    public Product create (Product product) {
        productData.add(product);
        productMap.put(product.getProductId(), product);
        return product;
    }
...
```
_Function-function_ yang dibuat semuanya tepat untuk melakukan _handling_ satu bagian program saja, tidak terlalu 
panjang, dan nama-namanya jelas mendeskripsikan apa yang dilakukan pada _function_ tersebut.


### 3. Objects and Data Structures
Contoh penerapannya dalam tutorial ini adalah penerapan _interface_ dan 
implementasinya di direktori `service` sebagai praktik terbaik dan untuk lebih rinci menjelaskan fungsi yang 
diimplementasikan.

### 4. Git Flow - Branching
Pada tutorial ini, adanya penerapan _branching_ untuk memisahkan pengembangan masing-masing bagian seperti `main`, 
`list-product`, `edit-product`, `delete-product`, dan `unit-test`.

## Unit and Functional Test

### 1. Unit Test
Setelah menyelesaikan penyusunan _unit test_, saya merasa lebih yakin terhadap kualitas kode yang telah saya buat. _Unit
test_ berperan penting dalam memastikan bahwa setiap bagian kecil dari kode beroperasi sesuai harapan dan memberikan
perlindungan terhadap perubahan yang tidak disengaja. Ketika membahas jumlah _unit test_ yang seharusnya ada dalam suatu
kelas, tidak ada jawaban pasti karena hal itu bergantung pada tingkat kompleksitas kelas dan fungsi yang perlu diuji.
Meski demikian, setiap metode yang memiliki logika berbeda atau memerlukan pengujian terpisah sebaiknya dilengkapi
dengan setidaknya satu _unit test_.

Untuk memastikan bahwa unit test sudah mencukupi untuk mengonfirmasi kebenaran program, penggunaan _code coverage_ dapat
menjadi alat yang bermanfaat. Meskipun mencapai 100% _code coverage_ dapat menambah kepercayaan, tetapi hal tersebut
tidak menjamin bahwa tidak ada _bug_ atau kesalahan dalam kode. Terlepas dari telah diuji secara menyeluruh, masih ada
kemungkinan adanya kasus-kasus _edge_ atau situasi tidak terduga yang tidak tercakup dalam _unit test_.

### 2. Functional Test
Dalam pembuatan _functional test suite_ baru untuk memverifikasi jumlah _item_ dalam daftar produk, perhatian terhadap
kebersihan kode sangat penting. Kehadiran duplikasi atau pengulangan kode, serta kurangnya modularitas, dapat
berdampak negatif pada kualitas keseluruhan dari kode yang dibuat. Apabila terdapat prosedur _setup_ dan variabel
_instance_ yang sama dengan _functional test suite_ sebelumnya, hal ini dapat mengindikasikan adanya duplikasi kode.
Ketidakpisahan _setup procedures_ ke dalam metode terpisah atau penggunaan terlalu banyak variabel _instance_ secara global
dapat mengakibatkan kurangnya modularitas pada kode. Oleh karena itu, disarankan untuk memisahkan _setup procedures_ ke
dalam metode terpisah agar dapat menghindari duplikasi kode dan meningkatkan tingkat modularitas.

</details>

<details>
<summary style="font-size:24px">Tutorial 2</summary>

## Code Quality
Ada beberapa isu ringan yang saya temukan ketika mengulas kembali kode, terutama kode pada modul `main`. Beberapa di antaranya
seperti penambahan deklarasi `final` untuk menambah keterbacaan dan keamanan pada bagian pembuatan _list_ `productData`.
Berikut merupakan _snippet_-nya,

```java
...
private final List<Product> productData = new ArrayList<>();
...
```

Selain itu saya juga sedikit mengubah implementasi injeksi kode yang pada awalnya menggunakan _field injection_ menjadi
_constructor injection_ agar dapat memudahkan pada saat melakukan tes kode dan menjaga modularitasnya. Berikut merupakan
contoh kode terbarunya pada bagian `ProductServiceImpl`,

```java
...
private final ProductRepository productRepository;

public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
}
...
```


## CI/CD Implementation
Ada beberapa aspek yang harus diperhatikan jika _workflow_ dapat dikatakan sudah sesuai dengan implementasi CI/CD,
seperti adanya _automated testing_, _continuous deployment_, dan adanya integrasi serta _deployment_ secara berkala. 
Hal-hal di atas sebenarnya sudah dijalankan pada tutorial ini. _Workflow automated testing_ terdapat pada 
`ci.yml` yang sekaligus mengeksekusi beberapa macam _unit test_ yang sudah dibuat, _continuous deployment_
juga telah diterapkan dengan adanya _deployment_ berkelanjutan pada _platform_ Koyeb, dan adanya integrasi serta 
_deployment_ yang apabila dilakukan _push_, _workflow_ akan mengarahkan untuk melakukan tes dan _deployment_ secara 
otomatis pada _branch_ tertentu.

</details>

<details>
<summary style="font-size:24px">Tutorial 3</summary>

## SOLID Principles
Berikut merupakan prinsip-prinsip SOLID yang saya terapkan pada _source code_,

### 1. SRP
_Single Responsibility Principle_ mengharuskan suatu kelas hanya boleh memiliki satu fungsi atau tujuan utama. Dengan 
adanya prinsip ini, kode diharapkan dapat terlihat sederhana dan tentu saja mudah untuk melakukan _maintenance_. Hal ini
saya terapkan pada saat memisah antara kelas `CarController` dengan `ProductController`. Pemisahan dilakukan untuk
menjaga modularitas kode dan mempermudah apabila nanti adanya modifikasi kode.

### 2. OCP
_Open-Close Principle_ merupakan salah satu prinsip yang mengharuskan unsur kode seperti kelas, modul, fungsi, dan
lainnya mempunyai sifat tertutup untuk modifikasi namun tetap terbuka untuk pengembangan. Dengan adanya prinsip ini,
_programmer_ dapat langsung melakukan ekstensi untuk menambah fitur atau kebutuhan suatu kelas tanpa harus memodifikasi 
kode asalnya sehingga kode dapat dikatakan _reusable_. Penerapan OCP dalam tutorial ini adalah ekstensi dari `Item`. 
Apabila diperhatikan, `Item` memiliki kerangka utama bagi kelas `Car` dan `Product` sehingga apabila _programmer_
membutuhkan kerangka yang ada di `Item`, mereka dapat langsung melakukan _extend_ di suatu kelas baru. Perhatikan juga
kelas `Car` yang sudah melakukan _extend_ dan menambahkan juga kebutuhan yang hanya ada pada kelas `Car` saja.

### 3. LSP
_Liskov Substitution Principle_ merupakan prinsip yang mengharuskan suatu kelas apabila memiliki sub-kelas, sub-kelas
tersebut harus mempunya _behavior_ yang sama dengan _parent class_-nya. Dalam tutorial ini, saya menerapkan LSP dalam 
`CarServiceImpl` yang mengimplementasikan `CarService`. `CarService` memang ditujukan sebagai kumpulan fitur yang
dimiliki oleh suatu kelas `Car`. Dengan adanya `CarService` yang sebenarnya merupakan ekstensi dari `ItemService`, fitur
kelas `Car` tidak akan bersinggungan dengan fitur yang berbeda dari kelas `Product` sehingga tidak akan melanggar LSP.

### 4. ISP
_Interface Segregation Principle_ sebenarnya memiliki kemiripan dengan SRP dan merupakan salah satu solusi agar LSP
terjaga, yaitu dengan memperbanyak _interface_ dengan harapan _interface_ tersebut dapat mewakilkan sautu fitur pada
suatu kelas dengan rinci. Hal ini sudah diterapkan pada _source code_ bagian pemisahan antara `CarService` dengan 
`ProductService`. Untuk saat ini, `ItemService` sebenarnya masih bisa sebagai perwakilan fitur untuk kedua kelas `Car`
dan `Product` namun tidak menutup kemungkinan bahwa di antara kedua kelas tersebut akan memiliki spesifikasi tambahan
ke depannya. Hal ini dapat mencegah adanya ketidaksesuaian fitur dan dapat mencegah adanya pelanggaran baik LSP maupun
SRP. 

### 5. DIP
_Dependency Inversion Principle_ merupakan prinsip yang menitikberatkan bahwa _high-level module_ dan _low-level module_
seharusnya bergantung kepada suatu abstrak modul jika memiliki keterkaitan tertentu. Hal ini saya terapkan dalam membuat
`CarController` yang tidak langsung bergantung pada `CarServiceImpl` melainkan dengan `CarService` yang 
diimpelementasikan oleh `CarServiceImpl` itu sendiri. Hal ini bertujuan meningkatkan fleksibilitas dari `CarController`
jika adanya penambahan fitur pada `CarService`.

## Advantage of Applying SOLID Principle
Manfaat yang cukup terasa pada saat mengimplementasikan prinsip SOLID adalah terstrukturnya kode sehingga _programmer_
tentu akan cukup mudah dalam memahami kode tersebut. Selain itu, dengan adanya pemisahan terstruktur, ada beberapa kode
dapat digunakan kembali sehingga akan meningkatkan produktivitas dari _programmer_. Hal ini dapat dilihat pada baik
pada _package_ `model` maupun `service` yang menerapkan modularitas dan _reusable_ dari suatu kumpulan kode. Tidak hanya
itu, dengan menerapakan _interface_ yang spesifik, hal itu akan meningkatkan fleksibilitas dari suatu kelas. Contohnya
pada implementasi baik `CarService` maupun `ProductService` yang tidak menutup kemungkinan bahwa kedua kelas tersebut
akan memiliki _behavior_ yang berbeda. 

## Disadvantage without SOLID Principle
Sebelum saya melakukan pemisahan `CarController` dengan `ProductController`, kode yang ada terlihat sangat panjang dan
cenderung membingungkan. Selain itu, saya juga sempat hanya melakukan penyalinan kode yang sebenarnya dapat digunakan
teknik seperti OCP pada _package_ model yang tentu saja akan menghemat lebih banyak waktu. Tidak hanya itu, apabila
hanya diterapkan satu _interface_ saja pada saat membagi antara `ProductServiceImpl` dan `CarServiceImpl`, akan ada
kemungkinan bahwa ke depannya masing-masing kelas tersebut memiliki keutuhan yang berbeda sehingga harus adanya
penyesuaian kembali yang kemungkinan cukup menguras tenaga, waktu, dan pikiran.

</details>

<details>
<summary style="font-size:24px">Tutorial 4</summary>

## Usefulness of TDD
TDD Flow sangat bermanfaat untuk continouous development dalam pengembangan software dimana software akan terus berkembang mengikuti permintaan client. TDD Flow memastikan bahwa software tetap memenuhi kualitas meskipun requirement terus berubah.

## F.I.R.S.T Principle
### Fast
Berbagai tes yang sudah dibuat cukup cepat sehingga dapat berjalan tanpa mengganggu _workflow_.

### Independent
Tes-tes yang dibuat sudah independen sehingga tidak bergantung dengan yang lain. 

### Repeatable
Tes yang dibuat hasilnya konsisten di setiap pengujian dengan catatan tidak adanya modifikasi dalam skala besar. 
Tes sudah _repeatable_ karena sudah _setup mock_ yang sama sehingga hasil tes dapat konsisten.

### Self Validating
Suatu tes dapat dikatakan _self validating_ apabila jelas dalam menguji suatu fitur. Tes yang dibuat sudah cukup 
_self salidating_ dengan penggunaan method assertEquals, assertNull, assertThrow, dan lainnya.

### Timely
Suatu tes sebaiknya menutupi baik _happy_ maupun _unhappy_ _paths_. Tes yang dibuat sudah menutupi sebagian besar 
_happy_ dan _unhappy paths_.
</details>