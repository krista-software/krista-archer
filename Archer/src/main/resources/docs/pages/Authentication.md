# Authentication

## Overview

The Archer Extension uses session-based authentication to securely connect to your RSA Archer instance. This page
provides detailed information about the authentication mechanism, security best practices, and troubleshooting guidance.

## Authentication Mode

The extension uses **Session Token Authentication** with username and password credentials.

### How It Works

1. **Credential Storage**: Your Archer credentials (username, password, instance name, and optional user domain) are
   securely stored in Krista
2. **Session Token Generation**: For each API request, the extension creates a session token by calling the Archer login
   API
3. **Token Usage**: The session token is included in the Authorization header of subsequent API requests
4. **Token Lifecycle**: Tokens are created on-demand for each request to ensure validity

## Authentication Flow

### Session Token Creation

When you make a catalog request, the following authentication flow occurs:

```
Step 1: Krista Extension initiates request
   ↓
Step 2: Extension calls Archer login endpoint
   POST /api/core/security/login
   Body: {
     "InstanceName": "your-instance",
     "Username": "your-username",
     "Password": "your-password",
     "UserDomain": "your-domain" (optional)
   }
   ↓
Step 3: Archer validates credentials
   ↓
Step 4: Archer returns session token
   Response: {
     "IsSuccessful": true,
     "RequestedObject": {
       "SessionToken": "abc123xyz..."
     }
   }
   ↓
Step 5: Extension uses token in API requests
   Header: Authorization: Archer session-id=abc123xyz...
   ↓
Step 6: Data is retrieved and returned to Krista
```

### Request Authentication

Each catalog request follows this pattern:

1. **Token Generation**: Create a new session token using stored credentials
2. **API Call**: Make the API request with the token in the Authorization header
3. **Response Handling**: Process the response and return data to Krista
4. **Retry Logic**: If the request fails, retry up to 2 times with a new token

## Required Permissions

The Archer user account must have the following permissions:

### Minimum Required Permissions

- **Read Access** to applications you want to query
- **Read Access** to field definitions
- **Read Access** to content records
- **API Access** enabled for the user account

### Recommended Permissions

For optimal functionality, ensure the user account has:

- Access to all applications you plan to integrate
- Permission to view field metadata
- Permission to read content records
- No unnecessary write or delete permissions (principle of least privilege)

## Security Best Practices

### 1. Use Dedicated Service Accounts

Create a dedicated service account for the Krista integration rather than using personal user accounts.

**Benefits**:

- Easier to track and audit API usage
- No disruption when employees leave
- Clearer separation of responsibilities

### 2. Apply Principle of Least Privilege

Grant only the minimum permissions required for the integration to function.

**Recommendations**:

- Read-only access to required applications
- No administrative privileges
- No access to sensitive applications not needed by Krista

### 3. Secure Credential Management

**Do**:

- ✅ Store credentials securely in Krista (automatically encrypted)
- ✅ Use strong, unique passwords
- ✅ Rotate passwords regularly
- ✅ Monitor account usage

**Don't**:

- ❌ Share credentials across multiple integrations
- ❌ Use personal accounts
- ❌ Store credentials in plain text
- ❌ Reuse passwords from other systems

### 4. Network Security

Ensure secure communication between Krista and Archer:

- **Use HTTPS**: Always use HTTPS for the Instance URL
- **Firewall Rules**: Configure firewalls to allow only necessary traffic
- **IP Whitelisting**: Consider restricting access to known IP addresses
- **VPN/Private Network**: Use VPN or private networks when possible

### 5. Monitor and Audit

Regularly review access and usage:

- **Audit Logs**: Review Archer audit logs for the service account
- **Unusual Activity**: Monitor for unexpected access patterns
- **Failed Attempts**: Investigate failed authentication attempts
- **Access Reviews**: Periodically review and validate permissions

### 6. Password Management

Follow these password best practices:

- **Complexity**: Use strong passwords with mixed characters
- **Length**: Minimum 12 characters recommended
- **Rotation**: Change passwords every 90 days or per your policy
- **No Sharing**: Never share passwords via email or chat
- **Update Krista**: Update the extension configuration when passwords change

## Token Management

### Session Token Lifecycle

- **Creation**: Tokens are created on-demand for each request
- **Duration**: Tokens are short-lived and used immediately
- **Expiration**: Tokens expire based on Archer's session timeout settings
- **Refresh**: New tokens are created for each request, eliminating the need for refresh logic

### Automatic Retry Mechanism

The extension includes built-in retry logic:

1. **Initial Request**: First attempt with a new session token
2. **Failure Detection**: If the request fails (non-200 status or timeout)
3. **Retry Attempt**: Retry up to 2 times with new session tokens
4. **Final Response**: Return the successful response or final error

This mechanism handles:

- Temporary network issues
- Session token expiration
- Transient server errors
- Timeout scenarios

## Troubleshooting Authentication

### Issue: "Authentication failed: Invalid username, password, or instance name"

**Symptoms**: Authentication fails with "Authentication failed: Invalid username, password, or instance name. Please verify your Archer credentials." message

**Possible Causes**:

- Incorrect username or password
- Wrong instance name
- Invalid user domain
- Account locked or disabled

**Resolution Steps**:

1. Verify credentials are correct in Archer
2. Test login directly in Archer web interface
3. Check that the instance name matches exactly
4. Confirm the user domain is correct (if used)
5. Ensure the account is active and not locked
6. Verify the account has not expired

### Issue: 401 Unauthorized Error

**Symptoms**: Requests fail with "Authorization failed: Your session has expired or you do not have permission to access this resource. Please verify your credentials and permissions." error

**Possible Causes**:

- Insufficient permissions
- Account disabled
- Session token expired
- API access not enabled

**Resolution Steps**:

1. Verify the user has read access to the application
2. Check that API access is enabled for the user
3. Confirm the account is active
4. Review Archer permissions for the user
5. Contact your Archer administrator to grant necessary permissions

### Issue: Connection Timeout

**Symptoms**: Requests timeout without completing

**Possible Causes**:

- Network connectivity issues
- Firewall blocking requests
- Archer instance unavailable
- Slow network connection

**Resolution Steps**:

1. Verify network connectivity to Archer instance
2. Check firewall rules allow HTTPS traffic
3. Confirm Archer instance is running and accessible
4. Test the Instance URL in a web browser
5. Contact your network administrator if issues persist

### Issue: Intermittent Authentication Failures

**Symptoms**: Authentication works sometimes but fails randomly

**Possible Causes**:

- Network instability
- Archer server load
- Session timeout settings
- Concurrent session limits

**Resolution Steps**:

1. Check Archer server health and performance
2. Review session timeout settings in Archer
3. Verify no concurrent session limits are being hit
4. Monitor network stability
5. Review Archer logs for detailed error information

## Authentication Validation

Before using the extension, validate your authentication setup:

### Validation Checklist

- [ ] Credentials are correct and tested in Archer web interface
- [ ] User account has API access enabled
- [ ] User has read permissions to required applications
- [ ] Instance URL is correct and accessible via HTTPS
- [ ] Instance Name matches Archer configuration exactly
- [ ] User Domain is correct (if applicable)
- [ ] Validate Attributes test passes successfully
- [ ] No firewall or network issues blocking access

### Testing Authentication

To test your authentication configuration:

1. Navigate to the extension setup page
2. Enter all required configuration parameters
3. Click **Validate Attributes**
4. Verify the validation succeeds
5. Check for any error messages
6. Review Archer audit logs to confirm successful login

## See Also

- [Extension Configuration](pages/ExtensionConfiguration.md) - Configuration parameters and setup
- [Get All Records Of Application](pages/GetAllRecordsOfApplication.md) - Example catalog request
- [Release Notes](pages/releaseNotes.md) - Version history and updates

