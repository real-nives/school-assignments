## D287 – JAVA FRAMEWORKS - TASK 1 - NICHOLAS IVES

C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.

    Changed line 14 from <title>My Bicycle Shop</title> to <title>Nick's Golf Shop</title>
    Changed line 19 from <h1>Shop</h1> to <h1>Nick's Golf Shop</h1>

D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.

    Created a new file about.html
        - Made a simple about page, attempting to follow the theme of mainscreen.html
        - Line 13 - Made the title "About Nick's Golf Shop"
        - Lines 17-19 - Headers showing that the page is an about page
        - Lines 22-26 - Short paragraph explaining the company
        - Line 28 - Link back to the main page

    Updated mainscreen.html
        - Line 20 - Added an "About Us" button under the "Nick's Golf Shop" header

    Updated MainScreenControllerr.java
        - Line 56-59 - Added mapping for the about page

E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.

    Updated BootStrapData.java
        - Line 43 - if statement checking to see if the parts repository is empty
        - Lines 44-68 - If the repository is empty, add the five golf parts with their corresponding name, price, and iventory
        - I used the constructor with no parameters and then used the set methods to update the information
        - Lines 70-74 - Saving the parts into the parts repository
        - Line 77 - if statement checking to see if the products repository is empty
        - Lines 79-83 - If the repository is empty, add the five golf products with their corresponding name, price, and inventory
        - I used the overloaded constructor for these to immediately set the values as the object was created
        - Lines 85-89 - Saving the products into the products repository

F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
- The “Buy Now” button must be next to the buttons that update and delete products.
- The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
- Display a message that indicates the success or failure of a purchase.


    Created buyfailure.html
        - A simple html page that shows the purchase was failed

    Created buysuccess.html
        - A simple html page that confirms the purchase was successful

    Updated mainscreen.html
        - Line 86 - Added a "Buy Now" button next to the "Update" and "Delete" buttons for the products
        - This button references the buyProduct mapping

    Updated AddProductController.java
        - Line 179 - Created the mapping for buyProduct
        - Line 184 - If the inventory of the selected product is 0 it returns the buyfailure.html screen
        - Line 186 - Otherwise, decrements the product inventory by 1 and saves, then returns the buysuccess.html screen

G.  Modify the parts to track maximum and minimum inventory by doing the following:
- Add additional fields to the part entity for maximum and minimum inventory.
- Modify the sample inventory to include the maximum and minimum fields.
- Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
- Rename the file the persistent storage is saved to.
- Modify the code to enforce that the inventory is between or at the minimum and maximum value.


    Updated mainscreen.html
        - Lines 39-40 - Added fields for Min Inventory and Max Inventory
        - Lines 49-50 - Referenced the minInv and maxInv attributes

    Updated InhousePartForm.html
        - Lines 16,18,21,24 - Added labels to the input fields
        - Lines 26-30 - Added new input fields for Min Inventory and Max Inventory
        - Lines 32-38 - Added error message if inventory value is not valid

    Updated OutsourcedPartForm.html
        - Lines 17,19,22,25 - Added labels to the input fields
        - Lines 25-31 - Added new input fields for Min Inventory and Max Inventory
        - Lines 33-39 - Added error message if inventory value is not valid

    Created InventoryValidator.java
        - Copied from EnufPartsValidator (changed instances of "EnufPartsValidator" to "InventoryValidator")
        - Lines 28-34 - if statement to check if the inventory value is less than min or more than max, throw error if it is

    Created ValidInventory.java
        - Copied from ValidEnufParts.java (changed instances of "ValidEnufParts" to "ValidInventory")

H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
- Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
- Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
- Display error messages when adding and updating parts if the inventory is greater than the maximum.


    I think I satisfied these requirements in the previous step.

I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.

    Updated PartTest.java
        - Lines 159-194 - Added unit tests for max and min setter and getter.

J.  Remove the class files for any unused validators in order to clean your code.

    Removed DeletePartValidator.java and ValidDeletePart.java as they were not being used.