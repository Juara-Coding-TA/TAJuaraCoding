# TAJuaraCoding

This project is a UI automation testing framework built using Java, Selenium WebDriver, and TestNG, managed with Maven. The framework focuses on automating user interactions and validating web application functionalities, specifically designed for HR or employee management systems with reporting modules including Leave (Cuti), Early Leave Permit (Izin Pulang Cepat), Late Permit (Izin Terlambat), and Attendance (Kehadiran).

## Key Features

### User Authentication
- Login functionality testing with positive and negative scenarios
- User authentication process validation
- Session management testing

### Navigation
- Automated navigation through various application sections
- Menu consistency and link validation testing
- Cross-browser navigation compatibility

### Dashboard Testing
The dashboard testing module provides comprehensive validation of the main HR dashboard functionality:

#### Key Metrics Validation
- **Employee Count Verification**: Validates the accuracy of total employee count display
- **Attendance Metrics**: Tests the correctness of attendance-related counters including:
  - Total Absences (Total Absensi)
  - Work From Home (WFH) count
  - Leave (Cuti) count  
  - Sick Leave (Sakit) count
  - Permission/Permit (Izin) count

#### Filter Functionality Testing
- **Date Range Filters**: 
  - Tests custom date range selection
  - Validates data refresh when date filters are applied
  - Ensures proper handling of edge cases (invalid dates, future dates)
- **Unit/Department Filters**:
  - Tests filtering by organizational units
  - Validates multi-select department functionality
  - Ensures proper data segregation by department

#### Approval Workflow Testing
The dashboard includes comprehensive testing for approval workflow tables:

- **Leave Approval Table**:
  - Tests leave request display and status updates
  - Validates approval/rejection workflow
  - Ensures proper data sorting and pagination
  
- **Overtime (Lembur) Approval Table**:
  - Tests overtime request processing
  - Validates time calculation accuracy
  - Ensures proper validator assignment

- **Correction (Koreksi) Approval Table**:
  - Tests attendance correction requests
  - Validates data modification workflows
  - Ensures audit trail functionality

#### Dashboard UI/UX Testing
- **Responsive Design**: Tests dashboard layout across different screen sizes
- **Loading Performance**: Validates dashboard load times and data refresh speeds
- **Interactive Elements**: Tests clickable charts, buttons, and navigation elements
- **Real-time Updates**: Validates automatic data refresh and live updates
- **Error Handling**: Tests behavior when data is unavailable or API calls fail

#### Data Integrity Testing
- **Cross-validation**: Ensures dashboard metrics match detailed report data
- **Calculation Accuracy**: Validates mathematical computations and aggregations
- **Data Consistency**: Tests data synchronization across different dashboard sections

### Reporting
- Filter functionality and data verification across various reports
- Report modules for Leave and Attendance
- Report data accuracy validation
- Export functionality testing

### Action Components
- Reusable components for common UI actions
- Consistent filter system implementation
- Generic validation methods

## System Requirements

- **Java Development Kit (JDK)** version 8 or higher
- **Apache Maven** version 3.6 or higher  
- **WebDriver** (ChromeDriver, GeckoDriver) configured in system PATH
- **Minimum RAM**: 4GB recommended for test execution
- **Browser Support**: Chrome, Firefox, Edge

## Installation and Setup

### 1. Clone Repository
```bash
git clone https://github.com/Juara-Coding-TA/TAJuaraCoding.git
cd TAJuaraCoding
```

### 2. Build Project
```bash
mvn clean install
```
This command will compile source code, run tests, and package the project.

### 3. Driver Configuration
Ensure WebDriver executables are available in your system PATH, or configure them in the test properties file.

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Suite
```bash
mvn test -DsuiteXmlFile="src/test/resources/testng.xml"
```

### Run Tests by Groups
```bash
mvn test -Dgroups="login,dashboard"
```

### Run Dashboard Tests Only
```bash
mvn test -Dgroups="dashboard"
```

### Run Tests with Specific Browser
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### Generate Test Reports
```bash
mvn surefire-report:report
```

## Key Dependencies

- **Java** - Primary programming language
- **Maven** - Build automation and dependency management
- **Selenium WebDriver** - Web automation framework
- **TestNG** - Testing framework for Java
- **WebDriverManager** - Automatic browser driver management
- **ExtentReports** - Test reporting framework
- **Apache POI** - Excel file handling for data-driven tests

## Project Structure

```
TAJuaraCoding/
├── pom.xml                    # Maven configuration
├── src/
│   ├── main/java/             # Main source code
│   │   └── com/juaracoding/tajuaracoding/
│   │       ├── pages/         # Page objects and actions
│   │       │   ├── actions/   # Reusable UI actions
│   │       │   ├── components/# UI components
│   │       │   ├── dashboard/ # Dashboard pages and validations
│   │       │   ├── laporan/   # Report pages
│   │       │   └── login/     # Login page
│   │       └── utils/         # Utility classes
│   ├── test/java/             # Test classes
│   │   └── com/juaracoding/tajuaracoding/
│   │       ├── Dashboard/     # Dashboard test suites
│   │       │   ├── DashboardMetricsTest.java
│   │       │   ├── DashboardFiltersTest.java
│   │       │   └── ApprovalWorkflowTest.java
│   │       ├── laporan/       # Report tests
│   │       └── login/         # Login tests
│   └── test/resources/        # Test resources
│       ├── testng.xml         # TestNG configuration
│       ├── test-data/         # Test data files
│       └── drivers/           # WebDriver executables
└── target/                    # Maven build output
    └── surefire-reports/      # Test execution reports
```

## Dashboard Test Coverage

### Test Scenarios Covered

1. **Metrics Accuracy Tests**:
   - Verify employee count matches HR database
   - Validate attendance calculations
   - Cross-check leave balances

2. **Filter Functionality Tests**:
   - Date range selection and validation
   - Department/unit filtering
   - Combined filter operations

3. **Workflow Integration Tests**:
   - Approval process validation
   - Status update verification
   - Notification system testing

4. **Performance Tests**:
   - Dashboard load time measurement
   - Data refresh performance
   - Concurrent user simulation

5. **Error Handling Tests**:
   - Network failure scenarios
   - Invalid data handling
   - Timeout management

## Usage Guidelines

### Adding New Test Cases
1. Create new test class in appropriate package
2. Extend from BaseTest or use TestNG annotations
3. Implement Page Object Model for better maintainability
4. Add test to relevant TestNG suite

### Best Practices
- Use Page Object Model for better code organization
- Implement explicit waits for test stability
- Create independent and repeatable test data
- Use meaningful assertions and error messages
- Follow naming conventions for test methods
- Implement proper test data cleanup

### Test Data Management
- Use external data files (Excel, JSON) for data-driven tests
- Implement test data factories for object creation
- Ensure test data isolation between test runs

## Continuous Integration

### Jenkins Integration
```bash
# Sample Jenkins pipeline command
mvn clean test -Dbrowser=headless -DsuiteXmlFile=testng.xml
```

### GitHub Actions
The project supports GitHub Actions for automated testing on pull requests and commits.

## Reporting and Analytics

### Test Reports
- **Surefire Reports**: Basic Maven test reports
- **ExtentReports**: Rich HTML reports with screenshots
- **Allure Reports**: Advanced reporting with trend analysis

### Dashboard Metrics Tracking
- Test execution time trends
- Pass/fail rate analytics
- Performance benchmarking

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Create a Pull Request

### Contribution Guidelines
- Follow existing code style and conventions
- Include unit tests for new functionality
- Update documentation as needed
- Ensure all tests pass before submitting PR

## Troubleshooting

### Common Issues
- **WebDriver Issues**: Ensure drivers are up-to-date and compatible with browser versions
- **Test Failures**: Check element locators and wait conditions
- **Performance Issues**: Adjust timeouts and implement proper waits

### Getting Help
- Check existing issues on GitHub
- Review test logs in `target/surefire-reports/`
- Enable debug logging for detailed information

## License

This project is licensed under the [MIT License](LICENSE.md).

## Support

For questions or issues:
- Create an issue on GitHub repository
- Contact the development team
- Check the project wiki for additional documentation

## Changelog

### Version 1.0.0
- Initial release with basic dashboard testing
- Login and navigation automation
- Report generation functionality

### Future Enhancements
- API integration testing
- Advanced analytics dashboard testing