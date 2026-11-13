# Tenant Management System

An Android app for landlords to record tenants, see total rent, and call tenants. Built in Kotlin as a group project for BBT 3.2: Mobile Application Development, using a UI layer and a data layer (ViewModel, LiveData and Room).

## Download
Download the latest APK from the [Releases](https://github.com/KaburaKimani/tenant-management-system/releases) page.

- v1.0: first release
- v1.1: updated UI colours (add this line only if you published v1.1)

## How to install
1. Open the Releases link on your Android phone and tap `app-release.apk` under **Assets**.
2. Open the downloaded file and, if asked, allow installing from this source.
3. Tap **Install**. If Play Protect warns you, tap **More details → Install anyway**.

## Features
- Register and log in
- Dashboard with number of tenants and total rent
- Add tenants and view all tenants (saved with Room)
- Call a tenant, options menu and navigation drawer

## Architecture
The code is split into a UI layer and a data layer. The UI layer may use the data layer, but the data layer never uses the UI layer.

```
ui/    Activities, Fragments and TenantViewModel (UI layer)
data/  Tenant, TenantDao, TenantDatabase, TenantRepository (data layer)

Screens → TenantViewModel (LiveData) → TenantRepository → Room
```

## Team
| Stage              | Member            | Admission No. | Pull request                                                    |
|--------------------|-------------------|---------------|-----------------------------------------------------------------|
| 1 Lead             | Elizabeth Kimani  | 189382        | Pushed directly to main (Stage 1)                               |
| 2 ViewModel        | Christine Kanaiza | 169281        | https://github.com/KaburaKimani/tenant-management-system/pull/1 |
| 3 Room             | Robert Irungu     | 189669        | https://github.com/KaburaKimani/tenant-management-system/pull/4 |
| 4 Screens          | Benjamin Kiuta    | 189018        | https://github.com/KaburaKimani/tenant-management-system/pull/2 |
| 5 Data integration | Amon Fore         | 169173        | https://github.com/KaburaKimani/tenant-management-system/pull/3 |
| 6 Release          | Tracy Njeri       | 189957        | https://github.com/KaburaKimani/tenant-management-system/pull/5 |