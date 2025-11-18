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
![data module]([https://via.placeholder.com/100x60?text=DATA](https://github.com/carlostom98/ToolBox/tree/main/persistence))

### **1.1 Gradle file Build Types

El archivo de gradle contiene la definición de un BASE_URL diferente para debug y release con la finalidad de realizar pruebas.

![build.gradle persistance](https://github.com/carlostom98/ToolBox/blob/main/persistence/build.gradle.kts)

---

### **2. `domain` (Reglas de negocio)**
Capa central independiente del framework.  
Incluye:  
- Entidades de dominio  
- Interfaces de repositorios  
- Casos de uso (UseCases)
- Cachear información obtenida mediante API en local DB con Room (a través de repositories de persistance). 

**Responsabilidad:** Regla y flujo de negocio.

🖼️  
![domain module]([https://via.placeholder.com/100x60?text=DOMAIN](https://github.com/carlostom98/ToolBox/tree/main/domain))

---

### **3. `app` (Capa de aplicación)**
Orquesta los casos de uso del dominio y provee dependencias al resto del sistema.  
Incluye:  
- Inyección de dependencias  
- Navegación  
- Configuración general  

**Responsabilidad:** Coordinar módulos y exponer servicios a la UI con Jetpack Compose.

🖼️  
![app module](https://github.com/carlostom98/ToolBox/tree/main/app)

---

### **4. `viewModel` (Lógica de UI)**
Contiene la lógica específica de presentación.  
Incluye:  
- ViewModels  
- Estados y eventos de UI  
- Adaptación de datos desde domain hacia la vista  

**Responsabilidad:** Actuar como puente entre UI y capas internas.

🖼️  
![vm module](https://github.com/carlostom98/ToolBox/tree/main/viewmodel)

---

### **5. `di` (repartir las depndencias de cada módulo)**
Contiene la lógica específica de inyección de dependencias con dagger hilt.  
Incluye:  
- Module data 
- Module Domain  
 
**Responsabilidad:** Centralizar la inyección de dependencias que necesita cada módulo.

🖼️  
![vm module](https://github.com/carlostom98/ToolBox/tree/main/viewmodel)

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
### ⭐ Testing
- The mockoon file for debugging purposes
![Mockoon file](https://github.com/carlostom98/ToolBox/blob/main/app/placeholder.json)
- Check api link

![json placeholder](https://jsonplaceholder.typicode.com/guide/)

---

## 📦 Resumen Final y Resultado final
Esta estructura modular mejora la mantenibilidad del proyecto y permite trabajar cada capa de forma autónoma. El flujo de datos es unidireccional:  
**Data → Domain → App → ViewModel → UI**  

### Home Screen

<img width="624" height="775" alt="Screenshot 2025-11-18 at 1 40 51 PM" src="https://github.com/user-attachments/assets/0869687f-3c3b-4e36-b0a7-b8a865293d7a" />

### Detail Screen
<img width="495" height="775" alt="Screenshot 2025-11-18 at 1 41 47 PM" src="https://github.com/user-attachments/assets/402fc776-4dff-46ec-9d98-d7c0f8c8577f" />

---

