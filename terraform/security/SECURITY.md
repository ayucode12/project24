This Terraform module defines the security and compliance configuration for the MultiCloud Deployment project.

## Objectives
- Enforce Identity and Access Management (IAM) policies
- Enable encryption for cloud storage
- Configure auditing and monitoring for compliance
- Implement least-privilege access across all environments

## Components
- **IAM Policy:** Provides read-only access for users
- **Encrypted S3 Bucket:** Ensures secure data storage
- **CloudTrail:** Enables activity auditing and compliance logging

## Notes
- Terraform code is stored in `security.tf`
- These configurations can be extended to Azure or GCP using provider-specific resources
- Do not run `terraform apply` unless you have valid cloud credentials
