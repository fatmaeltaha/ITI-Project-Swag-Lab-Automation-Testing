# Swag Labs – UI Automation Testing Project

This repository contains a complete **UI Automation Testing** framework for the web application **Swag Labs**, built using **Selenium WebDriver** and **TestNG**.  
The project demonstrates a full testing lifecycle, including framework design, scenario creation, and test reporting.

---

## Project Overview

This project includes:

- Requirement Specifications Document  
- Test Case Execution Report  
- Building a structured automation framework  
- Writing valid test scenarios  
- Writing invalid test scenarios  
- Creating end-to-end (E2E) test scenarios  
- Generating detailed test reports using Allure  

---

##  Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **Allure Report**
- **POM (Page Object Model) design pattern**

---

## Framework Structure

The automation framework is designed for high maintainability and follows industry-standard architecture:

### Page Object Model (POM)
Each page of the application has its own class for elements & actions to ensure clean, reusable code.

### TestNG Integration
Used for:
- Test grouping  
- Annotations  
- Assertions  
- Test management  

###  Listeners
Custom TestNG Listeners handle:
-  logging  
- Screenshot capturing  
- Allure reporting integration  

### Utils Package
Includes helper classes for:
- WebDriver management  
- Reading test data  
- Common actions 

### Allure Reporting
Provides:
- Detailed test steps  
- Screenshots  
- Timeline view  
- Trend statistics  

---

## Test Scenarios

### **Valid Test Scenarios**
Examples:
- Valid login  
- Adding products to the cart  
- Successful checkout  

### **Invalid Test Scenarios**
Examples:
- Invalid username/password  
- Empty fields    

### **End-to-End Test Scenarios**
Complete workflow:
**Login → Add products → Proceed to checkout → Complete order**

---
## Requirement specification documentation
 doc Link:
https://docs.google.com/document/d/1Bn31ZqJfKfytwfmZDogZ_k2aT99SU_ZRikKw1WgK7c4/edit?usp=sharing

---

## Testcase execution report
 report Link:
https://docs.google.com/spreadsheets/d/1jX4--D4k1_C7YMka-oiPMCXSmhe0biLWGaPtG-Trp5g/edit?usp=sharing

---
 

## Demo Video
 Demo Drive Link:
 https://drive.google.com/file/d/1j0YE-5PMEt2QHl9ov_FTabQiskG6lfK7/view?usp=sharing

---

## Application Under Test
This project tests the Swag Labs website:  
https://www.saucedemo.com/

---

##  How to Run the Project

1. Install **Java** and **Maven**  
2. Clone the repository  
3. Run test XML file:


