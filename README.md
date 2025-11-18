# ToolBox
# 🧩 Arquitectura por Módulos  
**App con separación en `data`, `domain`, `app` y `viewModel`**

Este proyecto implementa una arquitectura modular clara y escalable, separando responsabilidades para facilitar el mantenimiento, pruebas y evolución del sistema.

---

## 📁 Estructura de Módulos

### **1. `data` (Persistencia)**
Encargado del acceso a datos y fuentes externas.  
Incluye:  
- Repositorios concretos  
- DAOs / APIs  
- Mappers hacia entidades de dominio  

**Responsabilidad:** Obtener y almacenar información sin lógica de negocio.

🖼️  
![data module](https://via.placeholder.com/100x60?text=DATA)

---

### **2. `domain` (Reglas de negocio)**
Capa central independiente del framework.  
Incluye:  
- Entidades de dominio  
- Interfaces de repositorios  
- Casos de uso (UseCases)  

**Responsabilidad:** Regla y flujo de negocio.

🖼️  
![domain module](https://via.placeholder.com/100x60?text=DOMAIN)

---

### **3. `app` (Capa de aplicación)**
Orquesta los casos de uso del dominio y provee dependencias al resto del sistema.  
Incluye:  
- Inyección de dependencias  
- Navegación  
- Configuración general  

**Responsabilidad:** Coordinar módulos y exponer servicios a la UI.

🖼️  
![app module](https://via.placeholder.com/100x60?text=APP)

---

### **4. `viewModel` (Lógica de UI)**
Contiene la lógica específica de presentación.  
Incluye:  
- ViewModels  
- Estados y eventos de UI  
- Adaptación de datos desde domain hacia la vista  

**Responsabilidad:** Actuar como puente entre UI y capas internas.

🖼️  
![vm module](https://via.placeholder.com/100x60?text=ViewModel)

---

## 🏗️ Diagrama General de Arquitectura

![architecture diagram](https://via.placeholder.com/700x300?text=DATA+→+DOMAIN+→+APP+→+VIEWMODEL)

---

## ⭐ Beneficios de esta Arquitectura
- Separación clara de responsabilidades  
- Fácil escalabilidad  
- Testeo independiente por módulo  
- Reutilización de lógica de negocio  
- Cambios de UI sin afectar el core del sistema  

---

## 📦 Resumen Final
Esta estructura modular mejora la mantenibilidad del proyecto y permite trabajar cada capa de forma autónoma. El flujo de datos es unidireccional:  
**Data → Domain → App → ViewModel → UI**  

---

