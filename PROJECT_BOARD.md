# Scrum Project Board - API Testing Mini Project

This document represents the project's Scrum board for Sprint 1.

---

## 📋 Board Columns

### 🗄️ 1. Product Backlog
- [ ] Implement optional Cucumber test runners for BDD execution
- [ ] Add CI/CD GitHub Action workflow for automated test execution on PRs

### 🎯 2. Sprint Backlog
- [ ] Add test cases for additional authentication endpoints (Verify Login APIs)

### ⚙️ 3. In Progress
*(None - all current sprint tasks are completed)*

### 🔍 4. In Review
*(None - all current sprint tasks are completed)*

###  5. Done
- [x] Create Testing framework skeleton (`pom.xml`, `.gitignore`, directories)
- [x] Create POJO response models for serialization/deserialization (`Product`, `Brand`, etc.)
- [x] Create ApiClient for RestAssured HTTP calls
- [x] Create ProductService containing product filtering business logic
- [x] Create Mockito unit tests for helper/service methods (`ProductServiceUnitTest`)
- [x] Create Integration tests for GET/POST `/productsList` happy/sad paths (`ProductsIntegrationTest`)
- [x] Create Integration tests for GET/PUT `/brandsList` happy/sad paths (`BrandsIntegrationTest`)
- [x] Create Integration tests for POST `/searchProduct` happy/sad paths (`SearchIntegrationTest`)
- [x] Create comprehensive `README.md` documentation
- [x] Verify complete test suite executes successfully via Maven

### 📝 6. Notes

#### **Project Goal**
Deliver a robust, structured API testing framework for `https://automationexercise.com` using Java, Maven, JUnit 5, RestAssured, Jackson, and Mockito, covering happy/sad paths and asserting all response payload values.

#### **Definition of Done (DoD)**
- [x] Framework builds and compiles without errors.
- [x] All unit tests (Mockito) pass successfully.
- [x] All integration tests (RestAssured) pass successfully.
- [x] Happy and sad path scenarios are covered for at least 3 endpoints.
- [x] Full response payload data schema is validated.
- [x] Local Git repository is initialized, staged, and committed with a clean log.
- [x] README.md is detailed with a class diagram and setup instructions.

---

## 👤 User Stories & Scenarios

### **User Story 1: Products Retrieval**
> **As a** Client Application,  
> **I want to** retrieve the list of all products,  
> **So that** I can display the catalog with correct names, prices, brands, and categories.

*   **Scenario 1.1 (Happy Path - GET)**
    *   **GIVEN** I have access to the `/productsList` API endpoint,
    *   **WHEN** I send a GET request,
    *   **THEN** the HTTP status code is 200, the payload `responseCode` is 200, and the products array is populated with valid IDs, names, prices, brands, and categories.
*   **Scenario 1.2 (Sad Path - POST)**
    *   **GIVEN** I have access to the `/productsList` API endpoint,
    *   **WHEN** I send a POST request,
    *   **THEN** the response contains payload `responseCode` 405 and the message `"This request method is not supported."`

### **User Story 2: Brands Retrieval**
> **As a** Client Application,  
> **I want to** retrieve the list of all product brands,  
> **So that** I can filter products by brand name in the shop catalog.

*   **Scenario 2.1 (Happy Path - GET)**
    *   **GIVEN** I have access to the `/brandsList` API endpoint,
    *   **WHEN** I send a GET request,
    *   **THEN** the HTTP status code is 200, the payload `responseCode` is 200, and the brands array contains all available brands with valid IDs and names.
*   **Scenario 2.2 (Sad Path - PUT)**
    *   **GIVEN** I have access to the `/brandsList` API endpoint,
    *   **WHEN** I send a PUT request,
    *   **THEN** the response contains payload `responseCode` 405 and the message `"This request method is not supported."`

### **User Story 3: Product Search**
> **As a** Shop User,  
> **I want to** search for a specific product keyword (e.g., "tshirt"),  
> **So that** I can quickly find matching products.

*   **Scenario 3.1 (Happy Path - POST)**
    *   **GIVEN** I have access to the `/searchProduct` API endpoint,
    *   **AND** I provide the form parameter `search_product` with value `"tshirt"`,
    *   **WHEN** I send a POST request,
    *   **THEN** the HTTP status code is 200, the payload `responseCode` is 200, and all returned products match or contain the search term in their name or category.
*   **Scenario 3.2 (Sad Path - POST Missing Parameter)**
    *   **GIVEN** I have access to the `/searchProduct` API endpoint,
    *   **AND** I do not provide the `search_product` parameter,
    *   **WHEN** I send a POST request,
    *   **THEN** the response contains payload `responseCode` 400 and the message `"Bad request, search_product parameter is missing in POST request."`
