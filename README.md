# 🚗 Gallerist Backend API

Bu proje, ikinci el araç satış platformu için geliştirilmiş **Spring Boot tabanlı backend API** uygulamasıdır.  
Sistem; galericiler (gallerist), müşteriler ve admin kullanıcıları üzerinden araç ilanlarının yönetilmesini sağlar.

---

## 🧩 Proje Amacı

Kullanıcıların:

- Araç ilanı oluşturabilmesi
- Araçları listeleyebilmesi
- Araç detaylarını görüntüleyebilmesi
- Satın alma işlemi gerçekleştirebilmesi
- Profil bilgilerini yönetebilmesi

amaçlanmıştır.

---

## 🛠️ Kullanılan Teknolojiler

- Java 17+
- Spring Boot
- Spring Security (JWT Authentication)
- Spring Data JPA
- PostgreSQL
- Lombok
- BeanUtils
- RESTful API mimarisi

---

## 👥 Kullanıcı Rolleri

### 🔐 ADMIN
- Sistem yönetimi
- Admin panel yetkileri

### 🏢 GALLERIST
- Araç ilanı oluşturur
- İlanlarını yönetir (CRUD)
- Satış durumlarını görüntüler

### 👤 CUSTOMER
- Araçları görüntüler
- Araç satın alabilir
- Profil bilgilerini tamamlar

---

## 🚗 Temel Özellikler

### 📌 Araç Yönetimi
- Araç ekleme
- Araç listeleme
- Araç güncelleme
- Araç silme

### 📌 Gallerist Car (İlan Sistemi)
- Araç + Gallerist ilişkisi
- Fiyat, plaka, para birimi desteği
- Hasar kaydı (damagePrice)
- Satış durumu (SALABLE / SALED)

---

## 💰 Satış Sistemi

- Araç satın alma işlemi customer üzerinden yapılır
- Satış sonrası araç durumu `SALED` olarak güncellenir
- Satılmış araçlar listelemelerden filtrelenir

---

## 🔐 Authentication & Security

- JWT tabanlı authentication
- Role-based authorization
- Spring Security filter chain
- UserDetails implementasyonu

```java
getAuthorities() -> ROLE_CUSTOMER / ROLE_GALLERIST / ROLE_ADMIN
```

---

## 👤 User Model

username
password
email
role
isProfileCompleted

---

## 📦 DTO Yapısı

- Entity → DTO dönüşümü yapılmaktadır
- BeanUtils kullanılarak mapping sağlanır
- Nested DTO yapısı:
    - Gallerist
    - Car
    - GalleristCar

---

## 📊 Business Rules

- Sadece `SALABLE` araçlar listelenir
- Satılmış araçlar ana sayfada gösterilmez
- GalleristCar üzerinden satış yönetilir
- Her kullanıcı sadece kendi ilanlarını görebilir

---

## 🔎 Filtreleme

- Marka (brand)
- Model (model)
- Pagination

---

## 📁 Proje Yapısı

controller/
service/
repository/
model/
dto/
enums/
config/
security/

---

## 🚀 Öne Çıkan Özellikler

- Clean architecture
- DTO mapping
- Role-based security
- Sales lifecycle management
- Modular service design

---

## 📌 Notlar

- Proje frontend (React) ile entegre çalışmaktadır
- API REST prensiplerine uygundur
- Dummy data ile test edilebilir

---

## 🎯 Gelecek Geliştirmeler

- Email verification
- Admin dashboard expansion
- Sales history reports
- Redis caching
- File upload (S3)

---

## 👨‍💻 Geliştirici

Abbas Çoban

---

## 📌 Durum

✔ Backend tamamlandı  
✔ Frontend entegre  
✔ Satış sistemi aktif  
✔ Role-based auth çalışıyor
