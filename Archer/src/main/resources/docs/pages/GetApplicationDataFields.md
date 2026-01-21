# Get Application Data Fields

## Overview

Retrieves metadata and definitions for all data fields configured in a specific Archer application. This request
provides information about field types, names, aliases, and other field properties.

## Request Details

- **Area**: Field
- **Type**: CHANGE_SYSTEM
- **Retry Support**: ✅ Yes - Automatic retry up to 2 attempts on failure

## Input Parameters

| Parameter Name | Type   | Required | Description                                             | Example |
|----------------|--------|----------|---------------------------------------------------------|---------|
| Application ID | Number | Yes      | The unique numeric identifier of the Archer application | `75`    |

### Parameter Details

#### Application ID

- **Purpose**: Identifies which Archer application's field definitions to retrieve
- **Format**: Numeric value (integer)
- **Example**: `75`, `100`, `250`
- **Validation**: Must be a valid application ID that exists in your Archer instance
- **Notes**: This is the numeric ID, not the application name or alias

## Output Parameters

| Parameter Name | Type      | Description                              |
|----------------|-----------|------------------------------------------|
| Response Info  | FreeForm  | Container for the response data          |
| Response       | Composite | Array of field definitions with metadata |

### Response Structure

The response contains an array of field definition objects. Each field object includes:

**Common Field Properties**:

- **Id**: Unique identifier for the field
- **Name**: Display name of the field
- **Alias**: Programmatic alias for the field
- **Type**: Field type (e.g., Text, Number, Date, Values List)
- **LevelId**: Level identifier in the application hierarchy
- **Guid**: Globally unique identifier for the field
- **IsRequired**: Whether the field is required
- **RelatedValuesListId**: ID of related values list (if applicable)

## Validation Rules

| Validation                      | Error Message                            | Resolution                                 |
|---------------------------------|------------------------------------------|--------------------------------------------|
| Application ID is empty or null | "One or more input parameter is invalid" | Provide a valid application ID             |
| Application ID is not a number  | "One or more input parameter is invalid" | Ensure Application ID is a numeric value   |
| Application ID does not exist   | "One or more field provided are invalid" | Verify the application ID exists in Archer |
| User lacks permission           | "you are not authorized"                 | Ensure user has access to the application  |

## Error Handling

### Input Errors (INPUT_ERROR)

**Cause**: Invalid or missing input parameters

**Common Scenarios**:

- Application ID is empty or null
- Application ID is not a valid number
- Application ID is negative

**Error Message**: "One or more input parameter is invalid"

**Resolution**:

1. Verify the Application ID is provided
2. Ensure Application ID is a positive number
3. Validate the parameter data type
4. Check that the ID is not zero or negative

### Logic Errors (LOGIC_ERROR)

**Cause**: Business logic validation failures

**Common Scenarios**:

- Application ID does not exist in Archer
- User does not have permission to access the application
- Application has no field definitions
- Application is deleted or inactive

**Error Message**: "One or more field provided are invalid"

**Resolution**:

1. Verify the application ID is correct
2. Check that the application exists in Archer
3. Confirm user permissions in Archer
4. Verify the application is active
5. Test the application ID in Archer directly

### System Errors (SYSTEM_ERROR)

**Cause**: System-level failures or Archer server issues

**Common Scenarios**:

- Archer instance is unavailable
- Network connectivity issues
- Archer API errors
- Database connection failures

**Error Message**: Varies based on the specific error

**Resolution**:

1. Check Archer instance availability
2. Verify network connectivity
3. Review Archer server logs
4. Contact your Archer administrator
5. Retry the request after a brief delay

### Authorization Errors

**Cause**: Authentication or permission issues

**Common Scenarios**:

- Invalid credentials
- Session token expired
- Insufficient permissions to view field definitions
- User does not have access to the application

**Error Message**: "you are not authorized"

**Resolution**:

1. Verify extension configuration is correct
2. Check user account has read access to the application
3. Ensure user has permission to view field metadata
4. Verify credentials are valid
5. Re-validate attributes in the extension setup

## Usage Examples

### Example 1: Retrieve Field Definitions for Incidents Application

**Scenario**: Get all field definitions for the Incidents application (ID: 75)

**Input**:

```
Application ID: 75
```

**Output**:

```json
{
  "Response": [
    {
      "Id": "1001",
      "Name": "Incident Title",
      "Alias": "incident_title",
      "Type": "Text",
      "LevelId": "2",
      "Guid": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "IsRequired": true,
      "MaxLength": 255
    },
    {
      "Id": "1002",
      "Name": "Severity",
      "Alias": "severity",
      "Type": "Values List",
      "LevelId": "2",
      "Guid": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "IsRequired": true,
      "RelatedValuesListId": "500"
    },
    {
      "Id": "1003",
      "Name": "Date Reported",
      "Alias": "date_reported",
      "Type": "Date",
      "LevelId": "2",
      "Guid": "c3d4e5f6-a7b8-9012-cdef-123456789012",
      "IsRequired": false
    }
  ]
}
```

**Result**: Returns all field definitions for the Incidents application

### Example 2: Retrieve Field Definitions for Risk Application

**Scenario**: Get field metadata for the Risk application (ID: 100)

**Input**:

```
Application ID: 100
```

**Output**:

```json
{
  "Response": [
    {
      "Id": "2001",
      "Name": "Risk Description",
      "Alias": "risk_description",
      "Type": "Text",
      "LevelId": "2",
      "Guid": "d4e5f6a7-b8c9-0123-def1-234567890123"
    },
    {
      "Id": "2002",
      "Name": "Risk Level",
      "Alias": "risk_level",
      "Type": "Values List",
      "LevelId": "2",
      "Guid": "e5f6a7b8-c9d0-1234-ef12-345678901234",
      "RelatedValuesListId": "600"
    }
  ]
}
```

**Result**: Returns all field definitions for the Risk application

### Example 3: Identify Required Fields

**Scenario**: Determine which fields are required for data entry

**Input**:

```
Application ID: 75
```

**Process**:

1. Retrieve all field definitions
2. Filter fields where IsRequired = true
3. Create list of required fields

**Result**: List of required fields for validation purposes

### Example 4: Retry Scenario

**Scenario**: Request fails initially but succeeds on retry

**Input**:

```
Application ID: 150
```

**Process**:

1. First attempt fails due to temporary network issue
2. Extension automatically retries with new session token
3. Second attempt succeeds
4. Field definitions are returned successfully

**Result**: Data is retrieved despite initial failure

## Business Rules

1. **Application ID Required**: The Application ID must be a valid numeric identifier
2. **Read Permissions**: The user must have read access to the application
3. **Field Visibility**: Only fields the user has permission to view are returned
4. **Automatic Retry**: Failed requests are automatically retried up to 2 times
5. **Metadata Only**: This request returns field definitions, not field values
6. **Active Applications**: Only active applications return field definitions

## Limitations

1. **Application ID Only**: This request requires the numeric ID, not the name or alias
2. **Field Visibility**: Only fields the user has permission to view are returned
3. **Application Access**: User must have access to the application in Archer
4. **No Field Values**: This request returns metadata only, not actual field values
5. **No Historical Definitions**: Only current field definitions are returned

## Best Practices

### 1. Cache Field Definitions

Field definitions typically don't change frequently:

- Cache the response to reduce API calls
- Set appropriate cache expiration (e.g., 24 hours)
- Refresh cache when application structure changes

### 2. Use for Dynamic Form Generation

Leverage field definitions to create dynamic forms:

```
1. Retrieve field definitions
2. Parse field types and properties
3. Generate form fields dynamically
4. Apply validation rules based on IsRequired
```

### 3. Validate Data Before Submission

Use field definitions to validate data:

- Check required fields are populated
- Validate data types match field types
- Ensure text fields don't exceed MaxLength
- Verify values lists contain valid options

### 4. Map Field Aliases to Names

Create a mapping for user-friendly display:

```
1. Retrieve field definitions
2. Create map of Alias → Name
3. Use for displaying field labels
4. Use aliases for programmatic access
```

### 5. Identify Field Relationships

Understand field dependencies:

- Identify fields with RelatedValuesListId
- Map field relationships
- Handle cascading field logic

## Common Use Cases

### 1. Dynamic Form Generation

```
Scenario: Create a data entry form based on application structure
Action:
  1. Retrieve field definitions for the application
  2. Parse field types and properties
  3. Generate form fields dynamically
  4. Apply validation rules
Result: Dynamic form is created matching Archer application structure
```

### 2. Data Validation

```
Scenario: Validate data before creating records
Action:
  1. Retrieve field definitions
  2. Check which fields are required
  3. Validate data types and constraints
  4. Ensure all required fields are populated
Result: Data is validated before submission
```

### 3. Field Mapping

```
Scenario: Map external data to Archer fields
Action:
  1. Retrieve field definitions
  2. Create mapping between external fields and Archer aliases
  3. Transform data using the mapping
  4. Submit data to Archer
Result: External data is correctly mapped to Archer fields
```

### 4. Application Documentation

```
Scenario: Generate documentation for an Archer application
Action:
  1. Retrieve field definitions
  2. Extract field names, types, and properties
  3. Generate documentation
  4. Include required fields and constraints
Result: Comprehensive application documentation is created
```

## Related Catalog Requests

- [Get All Records Of Application](pages/GetAllRecordsOfApplication.md) - Retrieve actual record data
- [Get Individual Record Detail](pages/GetIndividualRecordDetail.md) - Get specific record details
- [Get Field Content](pages/GetFieldContent.md) - Retrieve field content by tracking ID

## Technical Implementation

### Helper Class

- **Class**: FieldDaoImpl
- **Package**: app.krista.extension.cyber_security.compliance.impl
- **Method**: getApplicationDataFieldDetails()
- **Service**: RestClient.get()

### API Endpoint

```
GET /api/core/system/fielddefinition/application/{applicationId}
```

### Retry Logic

- **Maximum Retries**: 2
- **Retry Conditions**: Non-successful response or timeout
- **Token Refresh**: New session token created for each retry

## Troubleshooting

### Issue: "Application Not Found" Error

**Cause**: Invalid application ID

**Solution**:

1. Verify the application ID is correct
2. Check that the application exists in Archer
3. Ensure you're using the numeric ID, not the name
4. Confirm the application is active

### Issue: Empty Response

**Cause**: Application has no fields or user lacks permissions

**Solution**:

1. Verify the application has field definitions
2. Check user permissions in Archer
3. Ensure user has access to view field metadata
4. Contact Archer administrator to grant permissions

### Issue: Missing Field Definitions

**Cause**: User lacks permission to view certain fields

**Solution**:

1. Review user permissions in Archer
2. Grant read access to required fields
3. Contact Archer administrator to adjust permissions
4. Verify field-level security settings

### Issue: How to Find Application ID

**Cause**: Application ID is not readily available

**Solution**:

1. Log into Archer web interface
2. Navigate to the application
3. Check the URL for the application ID parameter
4. Or use Archer's API to search for applications
5. Contact Archer administrator for assistance

## See Also

- [Extension Configuration](pages/ExtensionConfiguration.md) - Setup and configuration
- [Authentication](pages/Authentication.md) - Authentication details
- [Get All Records Of Application](pages/GetAllRecordsOfApplication.md) - Retrieve application records

