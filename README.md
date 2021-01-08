# Lasso Redirects Demo

### Project Description

This is a sample Spring Boot project that demonstrates the technique of multiple parallel HTTP redirects in order to exchange any user-specific data between your service and any number of your partner services.

All you have to do is to embed a single script HTML tag into the page your users interact with. 

Brought to you by the Lasso Marketing team.

* [Official Lasso Marketing Website](https://lasso-os.com)

### Usage
Run the project as a Spring Boot application and open in any browser `test.html` page, open inspector, 
hit the network tab to see how parallel redirects work.
To inspect the code, start with `IdentityRedirectsController`.

Our partners API is imitated by `PartnersAPI` controller.

To see why we can't sync with as many partners as we want, using a single redirects chain, open `test-infinite-loop-page.html`
and see `InfiniteLoopController`.

Any questions you would like to ask according to the project, you can send to [davidg@lassomarketing.io](mailto:davidg@lassomarketing.io)
