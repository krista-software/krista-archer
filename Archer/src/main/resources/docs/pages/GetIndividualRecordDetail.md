# Get Individual Record Detail

## Overview

Retrieves detailed information for a specific record in an Archer application using the application alias and tracking
ID. This request returns all field values for the specified record.

## Request Details

- **Area**: Content
- **Type**: CHANGE_SYSTEM
- **Retry Support**: ✅ Yes - Automatic retry up to 2 attempts on failure

## Input Parameters

| Parameter Name   | Type   | Required | Description                                               | Example     |
|------------------|--------|----------|-----------------------------------------------------------|-------------|
| Application Name | Text   | Yes      | The alias of the Archer application containing the record | `Incidents` |
| Tracking ID      | Number | Yes      | The unique tracking ID of the record to retrieve          | `308626`    |

### Parameter Details

#### Application Name

- **Purpose**: Identifies which Archer application contains the record
- **Format**: Text string representing the application alias (not the display name)
- **Example**: `Incidents`, `Risks`, `Policies`
- **Validation**: Must be a valid application alias in your Archer instance
- **Notes**: This is case-sensitive and must match the alias exactly

#### Tracking ID

- **Purpose**: Uniquely identifies the specific record to retrieve
- **Format**: Numeric value
- **Example**: `308626`, `400001`, `500123`
- **Validation**: Must be a valid tracking ID that exists in the specified application
- **Notes**: The tracking ID is unique within each application

## Output Parameters

| Parameter Name | Type      | Description                          |
|----------------|-----------|--------------------------------------|
| Response Info  | FreeForm  | Container for the response data      |
| Response       | Composite | Record details with all field values |

### Response Structure

The response contains a single record with all field values. The exact fields depend on the application's configuration.

**Common Fields**:

- **Tracking_ID**: The unique identifier for the record
- **First_Published**: Timestamp when the record was first created
- **Last_Updated**: Timestamp when the record was last modified
- Additional fields specific to the application and record

## Validation Rules

| Validation                      | Error Message                            | Resolution                                       |
|---------------------------------|------------------------------------------|--------------------------------------------------|
| Application Name is empty       | "One or more input parameter is invalid" | Provide a valid application alias                |
| Tracking ID is empty or null    | "One or more input parameter is invalid" | Provide a valid tracking ID                      |
| Application Name does not exist | "One or more field provided are invalid" | Verify the application alias exists in Archer    |
| Tracking ID does not exist      | "One or more field provided are invalid" | Verify the tracking ID exists in the application |
| Tracking ID is not a number     | "One or more input parameter is invalid" | Ensure Tracking ID is a numeric value            |

## Error Handling

### Input Errors (INPUT_ERROR)

**Cause**: Invalid or missing input parameters

**Common Scenarios**:

- Application Name is empty or null
- Tracking ID is empty or null
- Tracking ID is not a valid number

**Error Message**: "One or more input parameter is invalid"

**Resolution**:

1. Verify all required parameters are provided
2. Check that Application Name is not empty
3. Ensure Tracking ID is a valid number
4. Validate parameter data types

### Logic Errors (LOGIC_ERROR)

**Cause**: Business logic validation failures

**Common Scenarios**:

- Application alias does not exist in Archer
- Tracking ID does not exist in the specified application
- User does not have permission to access the record
- Record has been deleted

**Error Message**: "One or more field provided are invalid"

**Resolution**:

1. Verify the application alias is correct
2. Confirm the tracking ID exists in the application
3. Check user permissions in Archer
4. Verify the record has not been deleted
5. Test the application and tracking ID in Archer directly

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
2. Check user account has read access to the application
3. Ensure user has permission to view the specific record
4. Verify credentials are valid
5. Re-validate attributes in the extension setup

## Usage Examples

### Example 1: Retrieve Incident Details

**Scenario**: Get detailed information for a specific incident

**Input**:

```
Application Name: "Incidents"
Tracking ID: 308626
```

**Output**:

```json
{
  "Response": {
    "Tracking_ID": "308626",
    "First_Published": "2023-06-01T05:59:29.143Z",
    "Last_Updated": "2023-06-01T06:03:55.347Z",
    "IPAddress": "192.168.1.100",
    "Status": "Open",
    "Severity": "High",
    "Description": "Unauthorized access attempt detected",
    "Assigned_To": "Security Team",
    "Date_Reported": "2023-06-01",
    "Resolution": null
  }
}
```

**Result**: Returns complete details for incident 308626

### Example 2: Retrieve Risk Record

**Scenario**: Get details for a specific risk assessment

**Input**:

```
Application Name: "Risks"
Tracking ID: 400001
```

**Output**:

```json
{
  "Response": {
    "Tracking_ID": "400001",
    "First_Published": "2023-05-15T10:30:00.000Z",
    "Last_Updated": "2023-05-20T14:45:00.000Z",
    "Risk_Level": "High",
    "Risk_Category": "Operational",
    "Owner": "John Doe",
    "Mitigation_Plan": "Implement additional controls",
    "Target_Date": "2023-06-30",
    "Current_Status": "In Progress"
  }
}
```

**Result**: Returns complete details for risk 400001

### Example 3: Retrieve Policy Document

**Scenario**: Get details for a specific policy

**Input**:

```
Application Name: "Policies"
Tracking ID: 500123
```

**Output**:

```json
{
  "Response": {
    "Tracking_ID": "500123",
    "First_Published": "2023-01-10T09:00:00.000Z",
    "Last_Updated": "2023-03-15T11:30:00.000Z",
    "Policy_Name": "Data Protection Policy",
    "Policy_Owner": "Compliance Team",
    "Effective_Date": "2023-02-01",
    "Review_Date": "2024-02-01",
    "Status": "Active",
    "Version": "2.1"
  }
}
```

**Result**: Returns complete details for policy 500123

### Example 4: Retry Scenario

**Scenario**: Request fails initially but succeeds on retry

**Input**:

```
Application Name: "Compliance"
Tracking ID: 600050
```

**Process**:

1. First attempt fails due to temporary network issue
2. Extension automatically retries with new session token
3. Second attempt succeeds
4. Record details are returned successfully

**Result**: Data is retrieved despite initial failure

## Business Rules

1. **Unique Tracking ID**: Each tracking ID is unique within an application
2. **Application Alias Required**: The Application Name must be the alias, not the display name
3. **Read Permissions**: The user must have read access to the specified application and record
4. **Field Visibility**: Only fields the user has permission to view are returned
5. **Automatic Retry**: Failed requests are automatically retried up to 2 times
6. **Deleted Records**: Deleted records cannot be retrieved

## Limitations

1. **Single Record Only**: This request retrieves only one record at a time
2. **Field Visibility**: Only fields the user has permission to view are returned
3. **Application Access**: User must have access to the application in Archer
4. **Record Existence**: The tracking ID must exist in the specified application
5. **No Historical Data**: Only current record data is returned, not historical versions

## Best Practices

### 1. Validate Tracking ID Before Request

Ensure the tracking ID exists before making the request:

- Retrieve tracking IDs using "Get All Records Of Application" first
- Validate tracking ID format (numeric)
- Handle cases where tracking ID may not exist

### 2. Handle Missing Records Gracefully

Implement error handling for non-existent records:

```
1. Make the request
2. Check for error responses
3. If record not found, handle appropriately
4. Log the error for troubleshooting
```

### 3. Use Correct Application Alias

- Verify the application alias in Archer before making requests
- Application aliases are case-sensitive
- Use the alias, not the display name

### 4. Cache Record Details When Appropriate

If record data doesn't change frequently:

- Cache the response to reduce API calls
- Set appropriate cache expiration
- Refresh cache when updates are expected

### 5. Combine with Other Requests

For efficient data retrieval:

```
1. Use "Get All Records Of Application" to get tracking IDs
2. Use "Get Individual Record Detail" for specific records needing full details
3. Use "Get Field Content" for field-specific queries
```

## Common Use Cases

### 1. Display Record Details in UI

```
Scenario: Show complete incident details in a dashboard
Action:
  1. User selects an incident from a list
  2. Application Name = "Incidents"
  3. Tracking ID = selected incident ID
  4. Retrieve and display all field values
Result: User sees complete incident information
```

### 2. Audit Trail Investigation

```
Scenario: Investigate when a record was created and last modified
Action:
  1. Retrieve record using tracking ID
  2. Extract First_Published and Last_Updated timestamps
  3. Compare with audit requirements
Result: Audit trail information is available for review
```

### 3. Data Validation

```
Scenario: Validate that a record contains expected values
Action:
  1. Retrieve record details
  2. Check specific field values
  3. Validate against business rules
  4. Flag discrepancies
Result: Data quality is verified
```

### 4. Record Enrichment

```
Scenario: Enrich external data with Archer record details
Action:
  1. External system provides tracking ID
  2. Retrieve full record details from Archer
  3. Combine with external data
  4. Process enriched dataset
Result: Complete information is available for processing
```

## Related Catalog Requests

- [Get All Records Of Application](pages/GetAllRecordsOfApplication.md) - Retrieve all records to find tracking IDs
- [Get Application Data Fields](pages/GetApplicationDataFields.md) - View field definitions for the application
- [Get Field Content](pages/GetFieldContent.md) - Retrieve field content by tracking ID

## Technical Implementation

### Helper Class

- **Class**: ContentDaoImpl
- **Package**: app.krista.extension.cyber_security.compliance.impl
- **Method**: getIndividualRecordDetail()
- **Service**: RestClient.get()

### API Endpoint

```
GET /{contentapi}/{applicationAlias}({trackingId})
```

### Retry Logic

- **Maximum Retries**: 2
- **Retry Conditions**: Non-successful response or timeout
- **Token Refresh**: New session token created for each retry

## Troubleshooting

### Issue: "Record Not Found" Error

**Cause**: Tracking ID does not exist in the application

**Solution**:

1. Verify the tracking ID is correct
2. Check that the record exists in Archer
3. Ensure the record has not been deleted
4. Verify you're using the correct application alias

### Issue: Incomplete Field Data

**Cause**: User lacks permission to view certain fields

**Solution**:

1. Review user permissions in Archer
2. Grant read access to required fields
3. Contact Archer administrator to adjust permissions
4. Verify field-level security settings

### Issue: "Application Not Found" Error

**Cause**: Invalid application alias

**Solution**:

1. Verify the application alias in Archer
2. Check for typos in the Application Name
3. Ensure the alias is case-correct
4. Confirm the application exists and is accessible

### Issue: Timeout Errors

**Cause**: Network issues or slow Archer response

**Solution**:

1. Check network connectivity
2. Verify Archer instance is responsive
3. Retry the request
4. Contact Archer administrator if issue persists

## See Also

- [Extension Configuration](pages/ExtensionConfiguration.md) - Setup and configuration
- [Authentication](pages/Authentication.md) - Authentication details
- [Get All Records Of Application](pages/GetAllRecordsOfApplication.md) - Retrieve all records

