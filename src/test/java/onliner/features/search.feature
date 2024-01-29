Feature: Onliner Search

  Background:
    Given Onliner Main page is opened

  Scenario: Search for Samsung TVs within specified criteria
    When I click 'Каталог' in header menu on Main page
    When I select 'Электроника' in main menu, select 'Телевидение' in sub menu and select 'Телевизоры' on Catalog page
    When I write price before 1500.0 on Product page
    And I select filters on Product page
      | Samsung   | Производитель |
      | 1920x1080 | Разрешение    |
      | 40        | Диагональ     |
      | 50        | Диагональ     |
    Then I verify that price is less than 1500.0
    Then I verify that product is only 'Samsung'
    Then I verify that resolution is only '1920x1080'
    Then I verify that  diagonal is between 40 and 50