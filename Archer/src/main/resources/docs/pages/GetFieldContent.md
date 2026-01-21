# Get Field Content

## Overview

Retrieves the content and field values for a specific record in Archer using the tracking ID. This request returns
detailed field content information for the specified record.

## Request Details

- **Area**: Field
- **Type**: CHANGE_SYSTEM
- **Retry Support**: ✅ Yes - Automatic retry up to 2 attempts on failure

## Input Parameters

| Parameter Name | Type   | Required | Description                          | Example  |
|----------------|--------|----------|--------------------------------------|----------|
| Tracking ID    | Number | Yes      | The unique tracking ID of the record | `308626` |

### Parameter Details

#### Tracking ID

- **Purpose**: Uniquely identifies the record whose field content should be retrieved
- **Format**: Numeric value (integer)
- **Example**: `308626`, `400001`, `500123`
- **Validation**: Must be a valid tracking ID that exists in Archer
- **Notes**: The tracking ID is unique across all applications in Archer

## Output Parameters

| Parameter Name | Type      | Description                       |
|----------------|-----------|-----------------------------------|
| Response Info  | FreeForm  | Container for the response data   |
| Response       | Composite | Field content data for the record |

### Response Structure

The response contains detailed field content information for the specified record. The exact structure depends on the
application and record configuration.

**Common Response Elements**:

- **Field Values**: All field values for the record
- **Field Metadata**: Field IDs, names, and types
- **Record Information**: Tracking ID, timestamps, and status
- **Related Records**: References to related records (if applicable)

## Validation Rules

| Validation                   | Error Message                            | Resolution                              |
|------------------------------|------------------------------------------|-----------------------------------------|
| Tracking ID is empty or null | "One or more input parameter is invalid" | Provide a valid tracking ID             |
| Tracking ID is not a number  | "One or more input parameter is invalid" | Ensure Tracking ID is a numeric value   |
| Tracking ID does not exist   | "One or more field provided are invalid" | Verify the tracking ID exists in Archer |
| User lacks permission        | "you are not authorized"                 | Ensure user has access to the record    |

## Error Handling

### Input Errors (INPUT_ERROR)

**Cause**: Invalid or missing input parameters

**Common Scenarios**:

- Tracking ID is empty or null
- Tracking ID is not a valid number
- Tracking ID is negative or zero

**Error Message**: "One or more input parameter is invalid"

**Resolution**:

1. Verify the Tracking ID is provided
2. Ensure Tracking ID is a positive number
3. Validate the parameter data type
4. Check that the ID is not zero or negative

### Logic Errors (LOGIC_ERROR)

**Cause**: Business logic validation failures

**Common Scenarios**:

- Tracking ID does not exist in Archer
- User does not have permission to access the record
- Record has been deleted
- Record is in an inaccessible state

**Error Message**: "One or more field provided are invalid"

**Resolution**:

1. Verify the tracking ID is correct
2. Confirm the record exists in Archer
3. Check user permissions in Archer
4. Verify the record has not been deleted
5. Test the tracking ID in Archer directly

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
- Insufficient permissions to view the record
- User does not have access to the application

**Error Message**: "you are not authorized"

**Resolution**:

1. Verify extension configuration is correct
2. Check user account has read access to the record
3. Ensure user has permission to view field content
4. Verify credentials are valid
5. Re-validate attributes in the extension setup

## Usage Examples

### Example 1: Retrieve Field Content for Incident

**Scenario**: Get field content for incident with tracking ID 308626

**Input**:

```
Tracking ID: 308626
```

**Output**:

```json
{
  "Response": {
    "ContentId": "308626",
    "LevelId": "2",
    "ModuleId": "75",
    "Fields": [
      {
        "FieldId": "1001",
        "FieldName": "Incident Title",
        "Value": "Unauthorized Access Attempt"
      },
      {
        "FieldId": "1002",
        "FieldName": "Severity",
        "Value": "High"
      },
      {
        "FieldId": "1003",
        "FieldName": "Date Reported",
        "Value": "2023-06-01T05:59:29.143Z"
      },
      {
        "FieldId": "1004",
        "FieldName": "Status",
        "Value": "Open"
      }
    ]
  }
}
```

**Result**: Returns detailed field content for the incident

### Example 2: Retrieve Field Content for Risk

**Scenario**: Get field content for risk with tracking ID 400001

**Input**:

```
Tracking ID: 400001
```

**Output**:

```json
{
  "Response": {
    "ContentId": "400001",
    "LevelId": "2",
    "ModuleId": "100",
    "Fields": [
      {
        "FieldId": "2001",
        "FieldName": "Risk Description",
        "Value": "Data breach vulnerability in legacy system"
      },
      {
        "FieldId": "2002",
        "FieldName": "Risk Level",
        "Value": "High"
      },
      {
        "FieldId": "2003",
        "FieldName": "Owner",
        "Value": "John Doe"
      }
    ]
  }
}
```

**Result**: Returns detailed field content for the risk

### Example 3: Extract Specific Field Values

**Scenario**: Retrieve field content and extract specific field values

**Input**:

```
Tracking ID: 500123
```

**Process**:

1. Retrieve field content
2. Parse the response
3. Extract specific field values by FieldId or FieldName
4. Use values in downstream processing

**Result**: Specific field values are extracted and available for use

### Example 4: Retry Scenario

**Scenario**: Request fails initially but succeeds on retry

**Input**:

```
Tracking ID: 600050
```

**Process**:

1. First attempt fails due to temporary network issue
2. Extension automatically retries with new session token
3. Second attempt succeeds
4. Field content is returned successfully

**Result**: Data is retrieved despite initial failure

## Business Rules

1. **Unique Tracking ID**: Each tracking ID is unique across all applications in Archer
2. **Read Permissions**: The user must have read access to the record
3. **Field Visibility**: Only fields the user has permission to view are returned
4. **Automatic Retry**: Failed requests are automatically retried up to 2 times
5. **Current Data Only**: Only current field values are returned, not historical data
6. **Deleted Records**: Deleted records cannot be retrieved

## Limitations

1. **Single Record Only**: This request retrieves content for one record at a time
2. **Field Visibility**: Only fields the user has permission to view are returned
3. **Record Access**: User must have access to the record in Archer
4. **Record Existence**: The tracking ID must exist in Archer
5. **No Historical Data**: Only current field values are returned
6. **No Application Context**: The request doesn't specify which application (tracking ID is globally unique)

## Best Practices

### 1. Validate Tracking ID Before Request

Ensure the tracking ID exists before making the request:

- Retrieve tracking IDs using "Get All Records Of Application" first
- Validate tracking ID format (numeric)
- Handle cases where tracking ID may not exist

### 2. Parse Response Structure

Understand the response structure:

```
1. Retrieve field content
2. Parse the Fields array
3. Map FieldId or FieldName to values
4. Handle missing or null values
```

### 3. Handle Field-Level Permissions

Account for field-level security:

- Not all fields may be returned
- Check for field existence before accessing
- Handle missing fields gracefully

### 4. Cache Field Content When Appropriate

If field content doesn't change frequently:

- Cache the response to reduce API calls
- Set appropriate cache expiration
- Refresh cache when updates are expected

### 5. Combine with Field Definitions

For complete understanding:

```
1. Use "Get Application Data Fields" to get field metadata
2. Use "Get Field Content" to get field values
3. Combine metadata and values for complete picture
```

## Common Use Cases

### 1. Display Record Details

```
Scenario: Show complete record details in a user interface
Action:
  1. User selects a record (tracking ID known)
  2. Retrieve field content using tracking ID
  3. Parse and display field values
  4. Format values based on field types
Result: User sees complete record information
```

### 2. Data Export

```
Scenario: Export record data for reporting
Action:
  1. Retrieve list of tracking IDs
  2. For each tracking ID, get field content
  3. Extract field values
  4. Format and export data
Result: Record data is exported for analysis
```

### 3. Field Value Extraction

```
Scenario: Extract specific field values for processing
Action:
  1. Retrieve field content
  2. Parse the Fields array
  3. Find specific field by FieldId or FieldName
  4. Extract and use the value
Result: Specific field values are available for processing
```

### 4. Data Synchronization

```
Scenario: Sync Archer data with external system
Action:
  1. Retrieve field content from Archer
  2. Map fields to external system schema
  3. Transform data as needed
  4. Update external system
Result: Data is synchronized between systems
```

## Related Catalog Requests

- [Get Individual Record Detail](pages/GetIndividualRecordDetail.md) - Alternative method to get record details
- [Get All Records Of Application](pages/GetAllRecordsOfApplication.md) - Retrieve tracking IDs
- [Get Application Data Fields](pages/GetApplicationDataFields.md) - Get field definitions

## Technical Implementation

### Helper Class

- **Class**: FieldDaoImpl
- **Package**: app.krista.extension.cyber_security.compliance.impl
- **Method**: getFieldContent()
- **Service**: RestClient.get()

### API Endpoint

```
GET /api/core/content/{trackingId}
```

### Retry Logic

- **Maximum Retries**: 2
- **Retry Conditions**: Non-successful response or timeout
- **Token Refresh**: New session token created for each retry

## Troubleshooting

### Issue: "Record Not Found" Error

**Cause**: Tracking ID does not exist in Archer

**Solution**:

1. Verify the tracking ID is correct
2. Check that the record exists in Archer
3. Ensure the record has not been deleted
4. Test the tracking ID in Archer web interface

### Issue: Incomplete Field Data

**Cause**: User lacks permission to view certain fields

**Solution**:

1. Review user permissions in Archer
2. Grant read access to required fields
3. Contact Archer administrator to adjust permissions
4. Verify field-level security settings

### Issue: Empty or Null Field Values

**Cause**: Fields may not have values or are not populated

**Solution**:

1. Verify the field has a value in Archer
2. Check if the field is optional
3. Handle null values in your code
4. Confirm field is not hidden or restricted

### Issue: Response Structure Different Than Expected

**Cause**: Response structure varies by application and record type

**Solution**:

1. Review the actual response structure
2. Parse the response dynamically
3. Don't assume fixed field positions
4. Use FieldId or FieldName to locate values

## See Also

- [Extension Configuration](pages/ExtensionConfiguration.md) - Setup and configuration
- [Authentication](pages/Authentication.md) - Authentication details
- [Get Individual Record Detail](pages/GetIndividualRecordDetail.md) - Alternative method for record details

