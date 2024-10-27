# TEK830 - Digitalization And AI in practice - Team 6
This is our (Team 6) project and GitHub repository for Digitalization and AI in practice, TEK830, a course at Chalmers University. Our project has the theme "How might we use smart home data to reduce utility costs in Europe?". With this theme, we then decided to do a concept of what an update to the IKEA Home Smart app could look like.

Some features that we decided to implement were an app tutorial, notifications and recommendations based on energy consumption. 
* An app tutorial to guide introduce and guide users.
* Concepts of notifications that can pop up and can be viewed later in a history page. 
* The energy spending categories, which is already present in the IKEA app through the “Energy Insights” feature is the foundation for our concept of recommendations. The recommendations can be products from IKEA or energy saving tips, which depend on your top energy spending categories. 

## Structure
This is a Java project that uses JavaFX and the H2 database engine.

### Tools
This is a Java project that uses JavaFX and the H2 database engine. However note that we had plans to do a database but did not work with it further due to time constraints and deadlines. Instead we do mock data with java code. We have some tests that uses JUnit. Maven is used as a tool for build automation. Note that there has been use of generative AI to speed up the development process. Which are tools like ChatGPT and GitHub Copilot.

### Running the project
Launching the project will open up a window for the app and lead you through the app tutorial. To simulate notifications you can press the “P”, “O”, “I”, “U”, “Y” keybinds to make them pop up. 

### Assets not made by us
Product recommendations are based on IKEA products. Therefore links, data and images to products are from IKEA, https://www.ikea.com/se/en/.

#### Images for tip recommendations
* Refrigeration
  * https://www.pexels.com/photo/silver-imac-on-white-table-4068317/
  * https://www.pexels.com/photo/crop-person-with-spoon-near-pan-5907623/
* Cooking
  * https://www.pexels.com/photo/woman-in-white-sweater-baking-cake-3992206/
  * https://www.pexels.com/photo/close-up-of-pink-cooking-pots-with-glass-lids-15245007/
* Hot water
  * https://www.pexels.com/photo/person-holding-a-faucet-5710332/
  * https://www.pexels.com/photo/photo-of-a-shower-head-4194864/
* Lighting
  * https://www.pexels.com/photo/hand-turning-off-bedside-lamp-15728026/
  * https://www.pexels.com/photo/romantic-window-let-the-light-in-11404846/

#### Text for these tips are inspired by these links
* Refrigeration
  * https://www.momentumenergy.com.au/blog/fridge-energy-efficiency-tips
  * https://www.nopec.org/residents/energy-tips/refrigerator
  * https://www.beko.com/bn-en/support/fridge-freezer--using--article/11-ways-to-improve-your-refrigerator%27s-energy-efficiency
* Cooking
  * https://energysavingtrust.org.uk/top-five-tips-save-energy-kitchen/
  * https://www.placesforpeople.co.uk/helpful-guides-tips/my-place-blog/how-to-save-energy-when-cooking/
* Hot water
  * https://www.energy.gov/energysaver/articles/15-ways-save-your-water-heating-bill
  * https://www.empirestateplumbing.com/about-us/news-and-events/34156-7-ways-to-make-your-hot-water-heater-more-energy-efficient.html
* Lighting 
  * https://beachesenergy.com/energy-savings/lighting-tips
  * https://www.leadingedgeenergy.com.au/blog/save-money-lighting/
