# IAM user with read-only permissions
resource "aws_iam_user" "developer" {
  name = "developer_user"
}

resource "aws_iam_policy" "readonly_policy" {
  name   = "ReadOnlyAccess"
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action   = ["ec2:Describe*", "s3:Get*"]
      Effect   = "Allow"
      Resource = "*"
    }]
  })
}

resource "aws_iam_user_policy_attachment" "attach" {
  user       = aws_iam_user.developer.name
  policy_arn = aws_iam_policy.readonly_policy.arn
}

# Encrypted S3 bucket
resource "aws_s3_bucket" "secure_bucket" {
  bucket = "secure-app-data"

  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm = "AES256"
      }
    }
  }
}

# CloudTrail auditing
resource "aws_cloudtrail" "main" {
  name                          = "multicloud-trail"
  s3_bucket_name                = aws_s3_bucket.secure_bucket.id
  include_global_service_events = true
  is_multi_region_trail         = true
  enable_log_file_validation    = true
}
