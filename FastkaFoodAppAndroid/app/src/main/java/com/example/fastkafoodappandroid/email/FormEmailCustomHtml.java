package com.example.fastkafoodappandroid.email;

public class FormEmailCustomHtml {
    public String emailCustom(String address, String total){
        String email ="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Document</title>\n" +
                "    <link\n" +
                "      rel=\"stylesheet\"\n" +
                "      href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"\n" +
                "      integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\"\n" +
                "      crossorigin=\"anonymous\"\n" +
                "    />\n" +
                "    <style>\n" +
                "      @import url(\"https://fonts.googleapis.com/css2?family=Montserrat&display=swap\");\n" +
                "      body {\n" +
                "        background-color: #ffe8d2;\n" +
                "        font-family: \"Montserrat\", sans-serif;\n" +
                "      }\n" +
                "      .card {\n" +
                "        border: none;\n" +
                "      }\n" +
                "      .logo {\n" +
                "        background-color: #eeeeeea8;\n" +
                "      }\n" +
                "      .totals tr td {\n" +
                "        font-size: 13px;\n" +
                "      }\n" +
                "      .footer {\n" +
                "        background-color: #eeeeeea8;\n" +
                "      }\n" +
                "      .footer span {\n" +
                "        font-size: 12px;\n" +
                "      }\n" +
                "      .product-qty span {\n" +
                "        font-size: 12px;\n" +
                "        color: #dedbdb;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"container mt-5 mb-5\">\n" +
                "      <div class=\"row d-flex justify-content-center\">\n" +
                "        <div class=\"col-md-8\">\n" +
                "          <div class=\"card\">\n" +
                "            <div class=\"text-left logo p-2 px-5\">\n" +
                "              <img src=\"https://i.imgur.com/2zDU056.png\" width=\"50\" />\n" +
                "            </div>\n" +
                "            <div class=\"invoice p-5\">\n" +
                "              <h5>Your order Confirmed!</h5>\n" +
                "              <span class=\"font-weight-bold d-block mt-4\">Hello, Chris</span>\n" +
                "              <span\n" +
                "                >You order has been confirmed and will be shipped in next two\n" +
                "                days!</span\n" +
                "              >\n" +
                "              <div\n" +
                "                class=\"payment border-top mt-3 mb-3 border-bottom table-responsive\"\n" +
                "              >\n" +
                "              </div>\n" +
                "              <div class=\"product border-bottom table-responsive\">\n" +
                "              </div>\n" +
                "              <div class=\"row d-flex justify-content-end\">\n" +
                "                <div class=\"col-md-5\">\n" +
                "                  <table class=\"table table-borderless\">\n" +
                "                    <tbody class=\"totals\">\n" +
                "                      <tr>\n" +
                "                        <td>\n" +
                "                          <div class=\"text-left\">\n" +
                "                            <span class=\"text-muted\">Subtotal</span>\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                          <div class=\"text-right\"><span>"+total+"</span></div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td>\n" +
                "                          <div class=\"text-left\">\n" +
                "                            <span class=\"text-muted\">Shipping Fee</span>\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                          <div class=\"text-right\"><span>5$</span></div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr class=\"border-top border-bottom\">\n" +
                "                        <td>\n" +
                "                          <div class=\"text-left\">\n" +
                "                            <span class=\"font-weight-bold\">Estimate Total</span>\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                          <div class=\"text-right\">\n" +
                "                            <span class=\"font-weight-bold\">"+address+"</span>\n" +
                "                          </div>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "              <p>\n" +
                "                We will be sending shipping confirmation email when the item\n" +
                "                shipped successfully!\n" +
                "              </p>\n" +
                "              <p class=\"font-weight-bold mb-0\">Thanks for shopping with us!</p>\n" +
                "              <span>Shoes Store</span>\n" +
                "            </div>\n" +
                "            <div class=\"d-flex justify-content-between footer p-3\">\n" +
                "              <span>Need Help? visit our <a href=\"#\"> help center</a></span>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";
        return email;
    }
}
