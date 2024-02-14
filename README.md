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