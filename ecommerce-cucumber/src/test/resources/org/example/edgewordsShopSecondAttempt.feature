Feature: E-commerce Order Placement

  Scenario Outline: Buy something and apply a coupon
    Given I can access the home page
    When I add an "<Item>" to my cart
    And I apply a coupon
    Then I can see 15% has been deducted

    Examples:
      | Item   |
      | Beanie |
      | Cap    |

  Scenario Outline: Order and item and confirm this as fact
    Given I have access to the shop page
    When I add an "<Item>" of clothing to my cart
    And I proceed through the purchasing process, entering "<FirstName>", "<LastName>", "<Country>", "<Address>", "<PostCode>", "<Phone>"
    Then I should see my order number in the accounts page

    Examples:
      | Item   | FirstName | LastName | Country | Address           | PostCode | Phone     |
      | Beanie | Ray       | Blanc    | France  | 9 Pourier Avinoue | 75008    | 123456789 |
      | Belt | Bill       | Blanket    | United Kingdom  | 9 Porter Road | EH15 2BY  | 987654321 |
