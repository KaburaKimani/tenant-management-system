# Tenant Management System

An Android app for landlords to record tenants, see total rent, and call tenants.

## Download
Latest APK: see **Releases** on the right of this page.

## Features
- Register and log in
- Dashboard with number of tenants and total rent
- Add tenants and view all tenants (saved with Room)
- Call a tenant, options menu and navigation drawer

## Architecture
ui/ Activities, Fragments and TenantViewModel (UI layer)
data/ Tenant, TenantDao, TenantDatabase, TenantRepository (data layer)

Screens → TenantViewModel (LiveData) → TenantRepository → Room

## Team
| Stage | Member            | Admission No. | Pull request |
|---|-------------------|---------------|--------------|
| 1 Lead | Elizabeth Kimani  | 189382        | -            |
| 2 ViewModel | Christine Kanaiza | 169281        | https://github.com/KaburaKimani/tenant-management-system/pull/1       |
| 3 Room | Robert Irungu     | 189669        | https://github.com/KaburaKimani/tenant-management-system/pull/4    |
| 4 Screens | Benjamin Kiuta    | 189018        | https://github.com/KaburaKimani/tenant-management-system/pull/2     |
| 5 Data integration | Amon Fore         | 169173        | https://github.com/KaburaKimani/tenant-management-system/pull/3       |
| 6 Release | Tracy Njeri       | 189957        | (link)       |