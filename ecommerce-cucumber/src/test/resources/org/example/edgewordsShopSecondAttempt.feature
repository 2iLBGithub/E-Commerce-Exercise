Feature: E-commerce Order Placement

  # Consider making this a scenario outline, and consider changing the item in when to something else

  Scenario: Buy something and apply a coupon
    Given I can access the home page
    When I add an item to my cart
    And I apply a coupon
    Then I can see 15% has been deducted

  Scenario Outline: Order and item and confirm this as fact
    Given I have access to the shop page
    When I add an item of clothing to my cart
    And I proceed through the purchasing process, entering "<FirstName>", "<LastName>", "<Country>", "<Address>", "<PostCode>", "<Phone>"
    Then I should see my order number in the accounts page

    Examples:
      | FirstName | LastName | Country | Address           | PostCode | Phone     |
      | Ray       | Blanc    | France  | 9 Pourier Avinoue | 75008    | 123456789 |
