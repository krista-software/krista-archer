# Krista Archer Extension

## Overview

The Krista Archer Extension enables seamless integration between Krista and RSA Archer, a leading Governance, Risk, and
Compliance (GRC) platform. This extension allows you to retrieve records, field definitions, and content data from your
Archer instance directly within your Krista workflows.

## Key Features

✅ **Record Retrieval** - Fetch all records from Archer applications with pagination support
✅ **Individual Record Details** - Retrieve detailed information for specific records using tracking IDs
✅ **Field Definition Management** - Access application field definitions and metadata
✅ **Content Data Access** - Query field content based on tracking IDs
✅ **Entity Search** - Search and filter Application and Questionnaire entities
✅ **Automatic Retry** - Built-in retry mechanism for improved reliability

## Quick Start Guide

1. **Add Extension to Workspace** - Select the Archer Extension from Catalog → Cyber Security → Compliance → Archer
2. **Configure Connection** - Enter your Archer instance URL, credentials, and instance details
3. **Validate Setup** - Test your connection using the Validate Attributes feature
4. **Start Using** - Begin making catalog requests to retrieve data from Archer

## Documentation Structure

### Getting Started

- [Extension Configuration](pages/ExtensionConfiguration.md) - Setup and configuration parameters
- [Authentication](pages/Authentication.md) - Authentication and security information

### Catalog Requests

#### Content Management

- [Get All Records Of Application](pages/GetAllRecordsOfApplication.md) - Retrieve all records with pagination
- [Get Individual Record Detail](pages/GetIndividualRecordDetail.md) - Fetch specific record details

#### Field Management

- [Get Application Data Fields](pages/GetApplicationDataFields.md) - Retrieve field definitions
- [Get Field Content](pages/GetFieldContent.md) - Access field content by tracking ID

### Additional Resources

- [Release Notes](pages/releaseNotes.md) - Version history and updates

## Support & Resources

- **Extension Version**: 2.0.4
- **Krista Service APIs Java**: 1.0.115
- **Global Catalog Version**: GC.2025.12.2

For additional support, refer to the individual documentation pages or contact your Krista administrator.
