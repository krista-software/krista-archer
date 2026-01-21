# Get All Records Of Application

## Overview

Retrieves all records from a specified Archer application using the application's alias. This request supports
pagination to handle large datasets efficiently.

## Request Details

- **Area**: Content
- **Type**: CHANGE_SYSTEM
- **Retry Support**: ✅ Yes - Automatic retry up to 2 attempts on failure

## Input Parameters

| Parameter Name   | Type   | Required | Description                                     | Example     |
|------------------|--------|----------|-------------------------------------------------|-------------|
| Application Name | Text   | Yes      | The alias of the Archer application to query    | `Incidents` |
| Page Size        | Number | No       | Number of records to return per page (max 1000) | `100`       |
| Item Index       | Number | No       | Starting index for pagination (0-based)         | `0`         |

### Parameter Details

#### Application Name

- **Purpose**: Identifies which Archer application to retrieve records from
- **Format**: Text string representing the application alias (not the display name)
- **Example**: `Incidents`, `Risks`, `Policies`
- **Validation**: Must be a valid application alias in your Archer instance
- **Notes**: This is case-sensitive and must match the alias exactly

#### Page Size

- **Purpose**: Controls how many records are returned in a single request
- **Format**: Numeric value
- **Default**: 1000 (if not specified)
- **Range**: 1 to 1000
- **Example**: `50`, `100`, `500`
- **Notes**: Use smaller page sizes for better performance with large datasets

#### Item Index

- **Purpose**: Specifies the starting position for pagination
- **Format**: Numeric value (0-based index)
- **Default**: 0 (if not specified)
- **Example**: `0` (first page), `100` (second page if page size is 100)
- **Notes**: Use in combination with Page Size to implement pagination

## Output Parameters

| Parameter Name | Type      | Description                        |
|----------------|-----------|------------------------------------|
| Response Info  | FreeForm  | Container for the response data    |
| Response       | Composite | Array of records with field values |

### Response Structure

The response contains a table with columns representing the fields in the Archer application. The exact columns depend
on the application's field configuration.

**Common Fields**:

- **Tracking_ID**: Unique identifier for the record
- **First_Published**: Date when the record was first created
- **Last_Updated**: Date when the record was last modified
- Additional fields specific to the application

## Validation Rules

| Validation                      | Error Message                            | Resolution                                    |
|---------------------------------|------------------------------------------|-----------------------------------------------|
| Application Name is empty       | "One or more input parameter is invalid" | Provide a valid application alias             |
| Application Name does not exist | "One or more field provided are invalid" | Verify the application alias exists in Archer |
| Page Size exceeds 1000          | Request may fail or be limited           | Use a page size of 1000 or less               |
| Invalid Item Index              | "One or more input parameter is invalid" | Ensure Item Index is a non-negative number    |

## Error Handling

### Input Errors (INPUT_ERROR)

**Cause**: Invalid or missing input parameters

**Common Scenarios**:

- Application Name is empty or null
- Page Size is negative
- Item Index is negative

**Error Message**: "One or more input parameter is invalid"

**Resolution**:

1. Verify all required parameters are provided
2. Check that Application Name is not empty
3. Ensure Page Size and Item Index are positive numbers
4. Validate parameter data types

### Logic Errors (LOGIC_ERROR)

**Cause**: Business logic validation failures

**Common Scenarios**:

- Application alias does not exist in Archer
- User does not have permission to access the application
- Application has no records

**Error Message**: "One or more field provided are invalid"

**Resolution**:

1. Verify the application alias is correct
2. Check user permissions in Archer
3. Confirm the application exists and is accessible
4. Test the application alias in Archer directly

### System Errors (SYSTEM_ERROR)

**Cause**: System-level failures or Archer server issues

**Common Scenarios**:

- Archer instance is unavailable
- Network connectivity issues
- Archer API errors

**Error Message**: Varies based on the specific error

**Resolution**:

1. Check Archer instance availability
2. Verify network connectivity
3. Review Archer server logs
4. Contact your Archer administrator

### Authorization Errors

**Cause**: Authentication or permission issues

**Common Scenarios**:

- Invalid credentials
- Session token expired
- Insufficient permissions

**Error Message**: "you are not authorized"

**Resolution**:

1. Verify extension configuration is correct
2. Check user account has read access to the application
3. Ensure credentials are valid
4. Re-validate attributes in the extension setup

## Usage Examples

### Example 1: Retrieve First 100 Records

**Scenario**: Get the first 100 records from the Incidents application

**Input**:

```
Application Name: "Incidents"
Page Size: 100
Item Index: 0
```

**Output**:

```json
{
  "Response": [
    {
      "Tracking_ID": "308626",
      "First_Published": "2023-06-01T05:59:29.143Z",
      "Last_Updated": "2023-06-01T06:03:55.347Z",
      "IPAddress": "192.168.1.100",
      "Status": "Open"
    },
    {
      "Tracking_ID": "308627",
      "First_Published": "2023-06-02T08:15:22.456Z",
      "Last_Updated": "2023-06-02T09:20:11.789Z",
      "IPAddress": "192.168.1.101",
      "Status": "Closed"
    }
  ]
}
```

**Result**: Returns the first 100 records from the Incidents application

### Example 2: Retrieve All Records (Default)

**Scenario**: Get all records without specifying pagination

**Input**:

```
Application Name: "Risks"
Page Size: (not specified)
Item Index: (not specified)
```

**Output**:

```json
{
  "Response": [
    {
      "Tracking_ID": "400001",
      "First_Published": "2023-05-15",
      "Last_Updated": "2023-05-20",
      "Risk_Level": "High",
      "Owner": "John Doe"
    }
  ]
}
```

**Result**: Returns up to 1000 records (default page size)

### Example 3: Paginated Retrieval

**Scenario**: Retrieve records in batches of 50

**Input (Page 1)**:

```
Application Name: "Policies"
Page Size: 50
Item Index: 0
```

**Input (Page 2)**:

```
Application Name: "Policies"
Page Size: 50
Item Index: 50
```

**Input (Page 3)**:

```
Application Name: "Policies"
Page Size: 50
Item Index: 100
```

**Result**: Retrieves all records in manageable batches of 50

### Example 4: Retry Scenario

**Scenario**: Request fails initially but succeeds on retry

**Input**:

```
Application Name: "Compliance"
Page Size: 200
Item Index: 0
```

**Process**:

1. First attempt fails due to temporary network issue
2. Extension automatically retries with new session token
3. Second attempt succeeds
4. Records are returned successfully

**Result**: Data is retrieved despite initial failure

## Business Rules

1. **Maximum Page Size**: The maximum number of records that can be retrieved in a single request is 1000
2. **Default Pagination**: If Page Size is not specified, the default value of 1000 is used
3. **Zero-Based Indexing**: Item Index starts at 0 for the first record
4. **Application Alias Required**: The Application Name must be the alias, not the display name
5. **Read Permissions**: The user must have read access to the specified application
6. **Automatic Retry**: Failed requests are automatically retried up to 2 times

## Limitations

1. **Maximum Records Per Request**: Limited to 1000 records per request
2. **Performance**: Large page sizes may impact performance
3. **Field Visibility**: Only fields the user has permission to view are returned
4. **Application Access**: User must have access to the application in Archer
5. **Network Timeout**: Very large requests may timeout on slow networks

## Best Practices

### 1. Use Appropriate Page Sizes

Choose page sizes based on your use case:

- **Small datasets** (< 100 records): Use default or small page size
- **Medium datasets** (100-1000 records): Use page size of 100-500
- **Large datasets** (> 1000 records): Implement pagination with page size of 100-500

### 2. Implement Pagination for Large Datasets

When retrieving large datasets:

```
1. Start with Item Index = 0
2. Set Page Size to a manageable number (e.g., 100)
3. Retrieve records
4. Increment Item Index by Page Size
5. Repeat until no more records are returned
```

### 3. Handle Empty Results

Always check if the response contains records before processing:

- Empty response may indicate no records exist
- May also indicate Item Index is beyond the total record count

### 4. Use Correct Application Alias

- Verify the application alias in Archer before making requests
- Application aliases are case-sensitive
- Use the alias, not the display name

### 5. Monitor Performance

- Track request duration for large datasets
- Adjust page size if requests are slow
- Consider caching results if data doesn't change frequently

## Common Use Cases

### 1. Export All Incidents

```
Scenario: Export all incident records for reporting
Action: 
  1. Set Application Name to "Incidents"
  2. Use default page size (1000)
  3. Implement pagination if more than 1000 records exist
Result: All incident records are retrieved and available for processing
```

### 2. Incremental Data Sync

```
Scenario: Sync new records created since last sync
Action:
  1. Retrieve all records with pagination
  2. Filter by Last_Updated date
  3. Process only records updated since last sync
Result: Only new or updated records are processed
```

### 3. Dashboard Data Retrieval

```
Scenario: Display recent records in a dashboard
Action:
  1. Set Application Name to desired application
  2. Set Page Size to 10 or 20
  3. Set Item Index to 0
Result: Most recent records are displayed in the dashboard
```

## Related Catalog Requests

- [Get Individual Record Detail](pages/GetIndividualRecordDetail.md) - Retrieve detailed information for a specific
  record
- [Get Application Data Fields](pages/GetApplicationDataFields.md) - View field definitions for an application
- [Get Field Content](pages/GetFieldContent.md) - Retrieve field content by tracking ID

## Technical Implementation

### Helper Class

- **Class**: ContentDaoImpl
- **Package**: app.krista.extension.cyber_security.compliance.impl
- **Method**: getRecordsOfTheApplication()
- **Service**: RestClient.get()

### API Endpoint

```
GET /{contentapi}/{applicationAlias}?$orderby=...&$top={pageSize}&$skip={itemIndex}
```

### Retry Logic

- **Maximum Retries**: 2
- **Retry Conditions**: Non-successful response or timeout
- **Token Refresh**: New session token created for each retry

## Troubleshooting

### Issue: No Records Returned

**Cause**: Application has no records or Item Index is too high

**Solution**:

1. Verify records exist in the application
2. Check Item Index is not beyond total record count
3. Try with Item Index = 0 to get first page

### Issue: "Application Not Found" Error

**Cause**: Invalid application alias

**Solution**:

1. Verify the application alias in Archer
2. Check for typos in the Application Name
3. Ensure the alias is case-correct
4. Confirm the application exists and is accessible

### Issue: Incomplete Data Returned

**Cause**: User lacks permission to view certain fields

**Solution**:

1. Review user permissions in Archer
2. Grant read access to required fields
3. Contact Archer administrator to adjust permissions

## See Also

- [Extension Configuration](pages/ExtensionConfiguration.md) - Setup and configuration
- [Authentication](pages/Authentication.md) - Authentication details
- [Get Individual Record Detail](pages/GetIndividualRecordDetail.md) - Retrieve specific record details

