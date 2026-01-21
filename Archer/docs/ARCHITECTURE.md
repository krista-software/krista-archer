# Archer Extension Architecture

## Overview

The Archer Extension is a Krista platform extension that integrates with RSA Archer GRC (Governance, Risk, and Compliance) platform. It provides capabilities to query applications, questionnaires, fields, and content records from Archer instances.

**Version:** 2.0.4  
**Domain:** Compliance  
**Ecosystem:** Cyber Security  
**Java Version:** 21

---

## Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                           KRISTA PLATFORM                                    │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                     Extension Runtime                                │    │
│  │  ┌─────────────┐    ┌─────────────┐    ┌─────────────────────────┐  │    │
│  │  │   Invoker   │───▶│  Extension  │───▶│   Catalog Requests      │  │    │
│  │  │   Context   │    │   Entry     │    │   (FieldArea,           │  │    │
│  │  └─────────────┘    │   Point     │    │    ContentArea)         │  │    │
│  │                     └─────────────┘    └───────────┬─────────────┘  │    │
│  └─────────────────────────────────────────────────────┼────────────────┘    │
└─────────────────────────────────────────────────────────┼────────────────────┘
                                                          │
                                                          ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                        ARCHER EXTENSION LAYERS                               │
│                                                                              │
│  ┌──────────────────────────────────────────────────────────────────────┐   │
│  │                    PRESENTATION LAYER (Catalog)                       │   │
│  │  ┌────────────┐  ┌─────────────┐  ┌────────────┐  ┌───────────────┐  │   │
│  │  │ FieldArea  │  │ ContentArea │  │ Entities   │  │    Stores     │  │   │
│  │  │ @Domain    │  │ @Domain     │  │ Application│  │ ApplicationSt │  │   │
│  │  │ @Catalog   │  │ @Catalog    │  │ Questionnai│  │ QuestionnairSt│  │   │
│  │  │ Request    │  │ Request     │  │            │  │               │  │   │
│  │  └─────┬──────┘  └──────┬──────┘  └────────────┘  └───────┬───────┘  │   │
│  └────────┼────────────────┼─────────────────────────────────┼──────────┘   │
│           │                │                                 │              │
│           ▼                ▼                                 ▼              │
│  ┌──────────────────────────────────────────────────────────────────────┐   │
│  │                      SERVICE LAYER (Interfaces)                       │   │
│  │  ┌─────────────┐  ┌──────────────┐  ┌─────────────┐  ┌────────────┐  │   │
│  │  │ Application │  │ ApplicationDa│  │ Questionnair│  │ ContentDao │  │   │
│  │  │ (Interface) │  │ (Interface)  │  │ (Interface) │  │ (Interface)│  │   │
│  │  └─────────────┘  └──────────────┘  └─────────────┘  └────────────┘  │   │
│  └──────────────────────────────────────────────────────────────────────┘   │
│           │                │                                 │              │
│           ▼                ▼                                 ▼              │
│  ┌──────────────────────────────────────────────────────────────────────┐   │
│  │                   IMPLEMENTATION LAYER (impl)                         │   │
│  │  ┌──────────────┐  ┌───────────────┐  ┌────────────────┐             │   │
│  │  │ApplicationImp│  │ApplicationDaoI│  │QuestionnaireDaoI│            │   │
│  │  │ (POJO Model) │  │ (Data Access) │  │ (Data Access)  │             │   │
│  │  └──────────────┘  └───────┬───────┘  └───────┬────────┘             │   │
│  │                            │                  │                       │   │
│  │  ┌──────────────────────────────────────────────────────────────┐    │   │
│  │  │                    Model & Utilities                          │    │   │
│  │  │  ┌────────────┐  ┌──────────┐  ┌────────────────────────┐    │    │   │
│  │  │  │    Root    │  │ Constant │  │ ResponseModification   │    │    │   │
│  │  │  │ Requested  │  │          │  │ HttpQueryParameters    │    │    │   │
│  │  │  │ Object     │  │          │  │                        │    │    │   │
│  │  │  └────────────┘  └──────────┘  └────────────────────────┘    │    │   │
│  │  └──────────────────────────────────────────────────────────────┘    │   │
│  └──────────────────────────────────────────────────────────────────────┘   │
│           │                                                                  │
│           ▼                                                                  │
│  ┌──────────────────────────────────────────────────────────────────────┐   │
│  │                     HTTP CLIENT LAYER                                 │   │
│  │  ┌────────────────────────────────────────────────────────────────┐  │   │
│  │  │                      RestClient                                 │  │   │
│  │  │  • Session Token Management                                     │  │   │
│  │  │  • HTTP GET/POST Operations                                     │  │   │
│  │  │  • Retry Logic (2 attempts)                                     │  │   │
│  │  │  • Response Handling                                            │  │   │
│  │  │  • OkHttp3 Client                                               │  │   │
│  │  └────────────────────────────────────────────────────────────────┘  │   │
│  └──────────────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                         RSA ARCHER GRC PLATFORM                              │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                        REST API Endpoints                            │    │
│  │  • /api/core/security/login                                          │    │
│  │  • /api/core/system/application                                      │    │
│  │  • /api/core/system/questionnaire                                    │    │
│  │  • /api/core/system/fielddefinition/{applicationId}                  │    │
│  │  • /contentapi/{applicationName}                                     │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## Layer Descriptions

### 1. Extension Entry Point (`ArcherExtension.java`)

The main entry point annotated with `@Extension`. Handles:
- **Connection Validation**: Validates invoker attributes (credentials)
- **Test Connection**: Tests connectivity to Archer instance
- **Custom Tabs**: Provides documentation tab

**Configuration Fields:**
| Field | Type | Required |
|-------|------|----------|
| InstanceName | Text | Yes |
| Username | Text | Yes |
| Password | Text | Yes |
| UserDomain | Text | No |
| Instance Url | Text | Yes |

### 2. Presentation Layer (Catalog)

#### Domain Areas
- **FieldArea**: Handles field-related catalog requests
- **ContentArea**: Handles content/record-related catalog requests

#### Entity Stores
- **ApplicationStore**: EntityStore implementation for Application entities
- **QuestionnaireStore**: EntityStore implementation for Questionnaire entities

#### Catalog Entities
- **Application**: Represents an Archer application
- **Questionnaire**: Represents an Archer questionnaire

### 3. Service Layer (Interfaces)

Defines contracts for data access:
- `Application` - Application model interface
- `ApplicationDao` - Application data access interface
- `Questionnaire` - Questionnaire model interface
- `QuestionnaireDao` - Questionnaire data access interface
- `ContentDao` - Content data access interface
- `FieldDao` - Field data access interface
- `SubForms` / `SubFormsDao` - Sub-form handling interfaces

### 4. Implementation Layer

#### Data Access Objects (DAOs)
- `ApplicationDaoImpl` - Fetches applications from Archer API
- `QuestionnaireDaoImpl` - Fetches questionnaires from Archer API
- `ContentDaoImpl` - Fetches content records from Archer API
- `FieldDaoImpl` - Fetches field definitions from Archer API
- `SubFormsDaoImpl` - Handles sub-form operations

#### Model Classes
- `Root` - Root JSON response wrapper
- `RequestedObject` - Archer API response object
- `UpdateInformation` - Audit information (create/update dates)

#### Utilities
- `Constant` - Application constants and shared Gson instance
- `HttpQueryParameters` - HTTP query parameter builder
- `ResponseModification` - Response transformation utilities

### 5. HTTP Client Layer (`RestClient.java`)

Handles all HTTP communication with Archer:
- **Authentication**: Session token creation via `/api/core/security/login`
- **HTTP Operations**: GET requests with retry logic
- **Response Handling**: Status code validation and error handling

---

## Data Flow Diagram

```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│   Krista     │     │   Catalog    │     │     DAO      │     │  RestClient  │
│   Platform   │     │    Area      │     │    Impl      │     │              │
└──────┬───────┘     └──────┬───────┘     └──────┬───────┘     └──────┬───────┘
       │                    │                    │                    │
       │  Catalog Request   │                    │                    │
       │───────────────────▶│                    │                    │
       │                    │                    │                    │
       │                    │  getData()         │                    │
       │                    │───────────────────▶│                    │
       │                    │                    │                    │
       │                    │                    │  get(endpoint)     │
       │                    │                    │───────────────────▶│
       │                    │                    │                    │
       │                    │                    │                    │ createSessionToken()
       │                    │                    │                    │─────────┐
       │                    │                    │                    │         │
       │                    │                    │                    │◀────────┘
       │                    │                    │                    │
       │                    │                    │                    │ HTTP GET
       │                    │                    │                    │─────────▶ Archer API
       │                    │                    │                    │
       │                    │                    │                    │◀──────── JSON Response
       │                    │                    │                    │
       │                    │                    │◀───────────────────│
       │                    │                    │   Response         │
       │                    │                    │                    │
       │                    │◀───────────────────│                    │
       │                    │   Parsed Data      │                    │
       │                    │                    │                    │
       │◀───────────────────│                    │                    │
       │   Map<String,Obj>  │                    │                    │
       │                    │                    │                    │
```

---

## Performance Limitations

### 1. Connection Pooling
| Limitation | Impact | Mitigation |
|------------|--------|------------|
| New OkHttpClient per request | High memory/connection overhead | Implement singleton client with connection pool |
| No connection reuse | Increased latency | Configure keep-alive connections |

### 2. Session Token Management
| Limitation | Impact | Mitigation |
|------------|--------|------------|
| Token created per request | Unnecessary API calls | Cache session tokens with TTL |
| No token refresh mechanism | Potential auth failures | Implement token refresh logic |

### 3. Data Fetching
| Limitation | Impact | Mitigation |
|------------|--------|------------|
| Full list fetch for search | Memory issues with large datasets | Implement server-side filtering |
| No pagination in API calls | Slow response for large datasets | Add pagination parameters |
| In-memory filtering | CPU overhead | Push filtering to Archer API |

### 4. Retry Logic
| Limitation | Impact | Mitigation |
|------------|--------|------------|
| Only 2 retry attempts | May fail on transient errors | Increase retry count with backoff |
| Fixed retry (no backoff) | May overwhelm server | Implement exponential backoff |
| Only timeout retries | Misses other transient errors | Retry on 5xx errors |

---

## Error Scenarios

### Authentication Errors

```
┌─────────────────────────────────────────────────────────────────┐
│                    AUTHENTICATION FLOW                           │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  ┌─────────┐    ┌───────────┐    ┌─────────────────────────┐   │
│  │ Request │───▶│ RestClient│───▶│ /api/core/security/login│   │
│  └─────────┘    └───────────┘    └────────────┬────────────┘   │
│                                               │                 │
│                      ┌────────────────────────┼─────────────┐   │
│                      │                        ▼             │   │
│                      │    ┌─────────────────────────────┐   │   │
│                      │    │     IsSuccessful = true?    │   │   │
│                      │    └──────────────┬──────────────┘   │   │
│                      │                   │                  │   │
│                      │         ┌─────────┴─────────┐        │   │
│                      │         ▼                   ▼        │   │
│                      │    ┌─────────┐        ┌──────────┐   │   │
│                      │    │  YES    │        │    NO    │   │   │
│                      │    │ Return  │        │  Throw   │   │   │
│                      │    │ Token   │        │ IllegalArg│  │   │
│                      │    └─────────┘        │ Exception │   │   │
│                      │                       └──────────┘   │   │
│                      └──────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
```

### HTTP Error Handling

| Status Code | Error Type | Exception Thrown |
|-------------|------------|------------------|
| 200, 201 | Success | None |
| 400 | Bad Request | `IllegalArgumentException("One or more input parameter is invalid")` |
| 401 | Unauthorized | `IllegalArgumentException("you are not authorized")` |
| 500 | Server Error | `IllegalArgumentException("One or more field provided are invalid")` |
| Other | Unknown | Returns response (may cause downstream issues) |

### Network Error Scenarios

```
┌─────────────────────────────────────────────────────────────────┐
│                    RETRY FLOW (GET Requests)                     │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│  ┌─────────────┐                                                │
│  │ GET Request │                                                │
│  └──────┬──────┘                                                │
│         │                                                        │
│         ▼                                                        │
│  ┌─────────────────┐                                            │
│  │ Attempt 1       │                                            │
│  └────────┬────────┘                                            │
│           │                                                      │
│     ┌─────┴─────┐                                               │
│     ▼           ▼                                               │
│ ┌───────┐  ┌─────────┐                                          │
│ │Success│  │ Failure │                                          │
│ │Return │  │         │                                          │
│ └───────┘  └────┬────┘                                          │
│                 │                                                │
│                 ▼                                                │
│         ┌─────────────────┐                                     │
│         │ Is Timeout AND  │                                     │
│         │ retries < 2?    │                                     │
│         └────────┬────────┘                                     │
│                  │                                               │
│            ┌─────┴─────┐                                        │
│            ▼           ▼                                        │
│       ┌────────┐  ┌──────────┐                                  │
│       │  YES   │  │    NO    │                                  │
│       │ Retry  │  │  Throw   │                                  │
│       │        │  │ Runtime  │                                  │
│       └───┬────┘  │ Exception│                                  │
│           │       └──────────┘                                  │
│           ▼                                                      │
│  ┌─────────────────┐                                            │
│  │ Attempt 2       │                                            │
│  └────────┬────────┘                                            │
│           │                                                      │
│     ┌─────┴─────┐                                               │
│     ▼           ▼                                               │
│ ┌───────┐  ┌─────────┐                                          │
│ │Success│  │ Failure │                                          │
│ │Return │  │ Return  │                                          │
│ │       │  │  null   │                                          │
│ └───────┘  └─────────┘                                          │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

---

## Package Structure

```
app.krista.extension.cyber_security.compliance/
├── ArcherExtension.java          # Extension entry point
├── RestClient.java               # HTTP client for Archer API
├── catalog/                      # Presentation layer
│   ├── CatalogueTypes.java       # Entity type converters
│   ├── ContentArea.java          # Content catalog requests
│   ├── FieldArea.java            # Field catalog requests
│   ├── entities/                 # Catalog entity definitions
│   │   ├── Application.java
│   │   └── Questionnaire.java
│   └── stores/                   # Entity store implementations
│       ├── ApplicationStore.java
│       └── QuestionnaireStore.java
├── impl/                         # Implementation layer
│   ├── ApplicationDaoImpl.java
│   ├── ApplicationImpl.java
│   ├── ContentDaoImpl.java
│   ├── FieldDaoImpl.java
│   ├── QuestionnaireDaoImpl.java
│   ├── QuestionnaireImpl.java
│   ├── SubFormsDaoImpl.java
│   ├── SubFormsImpl.java
│   ├── model/                    # Data models
│   │   ├── RequestedObject.java
│   │   ├── Root.java
│   │   └── UpdateInformation.java
│   └── util/                     # Utilities
│       ├── Constant.java
│       ├── HttpQueryParameters.java
│       └── ResponseModification.java
└── service/                      # Service interfaces
    ├── Application.java
    ├── ApplicationDao.java
    ├── ContentDao.java
    ├── FieldDao.java
    ├── Questionnaire.java
    ├── QuestionnaireDao.java
    ├── SubForms.java
    └── SubFormsDao.java
```

---

## Recommendations

### High Priority
1. **Implement Connection Pooling**: Use singleton OkHttpClient with connection pool
2. **Cache Session Tokens**: Avoid creating new tokens for every request
3. **Add Server-Side Filtering**: Push search filters to Archer API

### Medium Priority
4. **Implement Exponential Backoff**: For retry logic
5. **Add Circuit Breaker**: Prevent cascading failures
6. **Improve Error Handling**: More specific exception types

### Low Priority
7. **Add Metrics/Telemetry**: Track API call latency and errors
8. **Implement Async Operations**: For long-running queries
9. **Add Request Timeout Configuration**: Make timeouts configurable

