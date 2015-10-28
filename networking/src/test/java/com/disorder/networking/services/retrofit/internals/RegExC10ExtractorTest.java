package com.disorder.networking.services.retrofit.internals;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class RegExC10ExtractorTest {

    private static final int expectedSize = 2;
    private static final String firstItem = "37647512DD";
    private static final String secondItem = "37647512TK";

    private RegExC10Extractor subjectUnderTest;
    private String rawHtml;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = new RegExC10Extractor();
        rawHtml = getRawHtml();
    }

    @Test
    public void testExtract() throws Exception {

        String[] itemsC10 = subjectUnderTest.extract(rawHtml);
        String firstResult = itemsC10[0];
        String secondResult = itemsC10[1];
        assertThat(itemsC10.length, is(expectedSize));
        assertThat(firstResult, is(firstItem));
        assertThat(secondResult, is(secondItem));

    }

    private String getRawHtml() {
        return "\n" +
                "<!DOCTYPE html>\n" +
                "<html class=\"no-js\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    \n" +
                "    \n" +
                "    <title>Segui il tuo ordine - yoox.com</title>\n" +
                "\n" +
                "    <link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\" />\n" +
                "    <link rel=\"icon\" href=\"/favicon.ico\" type=\"image/x-icon\" />\n" +
                "    <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"http://cdn2.yoox.biz/yoox14/apple-touch-icon-144x144-precomposed.png\">\n" +
                "    <meta http-equiv=\"Content-Language\" content=\"IT\" />\n" +
                "    <meta name=\"copyright\" content=\"\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta http-equiv=\"pragma\" content=\"no-cache\" />\n" +
                "    <meta http-equiv=\"Expires\" content=\"0\" />\n" +
                "    <meta name=\"revisit-after\" content=\"1\" />\n" +
                "    <meta name=\"google-site-verification\" content=\"NV_tCU7BIW59oBzqqdDXrE89i264rlRy5JsQEdAc7E0\" />\n" +
                "    <meta name=\"verify-v1\" content=\"lBXySdVsGSMumN2yPp+0KDvrsjEGIGIPdCXwBwEsLY8=\" />\n" +
                "    <meta name=\"p:domain_verify\" content=\"2f78cda6b93312de0ccda12b9422d54a\" />\n" +
                "    <meta http-equiv=\"imagetoolbar\" content=\"no\" />\n" +
                "    <meta name=\"SKYPE_TOOLBAR\" content=\"SKYPE_TOOLBAR_PARSER_COMPATIBLE\" />\n" +
                "    <meta name=\"format-detection\" content=\"telephone=no\" />\n" +
                "\n" +
                "    \n" +
                "    <meta name=\"description\" content=\" - yoox.com\" />\n" +
                "    <meta name=\"keywords\" content=\" - yoox.com\" />\n" +
                "    <meta name=\"robots\" content=\"index,follow\" />\n" +
                "    <meta name=\"labelTitle\" content=\"H/T/dept-all\" />\n" +
                "    <meta name=\"labelDescription\" content=\"H/D/dept-all\" />\n" +
                "    <link rel=\"canonical\" href=\"http://www.yoox.com/it/D\" />\n" +
                "\n" +
                "    \n" +
                "    \n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.yoox.com/_css_/0/181/YOOX10/Views/Shared/baselib.css\" media=\"screen\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.yoox.com/_css_/0/248/YOOX10/Views/Shared/mainlib.css\" media=\"screen\"/><link rel=\"stylesheet\" type=\"text/css\" href=\"http://www.yoox.com/_css_/0/7/Help/Views/Shared/main.css\" media=\"screen\"/>\n" +
                "    \n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    var jsInit = {\n" +
                "\n" +
                "        links: {\n" +
                "            IMG_PATH: 'http://cdn2.yoox.biz/',\n" +
                "            IMG_PRODUCT_PATH: 'http://images.yoox.com/items/',\n" +
                "            IMG_SSL_PATH: 'https://media.yoox.biz/',\n" +
                "            IMG_ORIGIN_PATH: 'http://images.yoox.com/',\n" +
                "            IMG_ZOOM_PATH: 'http://imgs.yoox.com/',\n" +
                "            COOKIE_DOMAIN: 'yoox.com',\n" +
                "            CACHED_DOMAIN: 'http://www.yoox.com',\n" +
                "            DYNAMIC_DOMAIN: 'http://www.yoox.com',\n" +
                "            SECURE_DOMAIN: 'https://secure.yoox.com',\n" +
                "            YOOXCOM_DOMAIN: 'http://www.yoox.com',\n" +
                "            DESKTOP_DOMAIN: 'http://www.yoox.com',\n" +
                "            MOBILE_DOMAIN: 'http://mobile.yoox.com',\n" +
                "            CCARE_DOMAIN: 'http://help.yoox.com',\n" +
                "            LayerFirstVisit: '',\n" +
                "            performa: '/IT/Context?noredir=1&gender=D',\n" +
                "            performaCrossDomain: '',\n" +
                "            sr: {\n" +
                "                ajaxUrl: '/IT/shoponline',\n" +
                "                url: ''\n" +
                "            },\n" +
                "            item: {\n" +
                "                itemDetailsUrl: '/IT/itemDetails',\n" +
                "                itemUrl: '/item?isoCode=IT',\n" +
                "                baynoteTrace: '/IT/Items/TraceRecommendation'\n" +
                "            },\n" +
                "            myoox: {\n" +
                "                homeUrl: '/IT/myoox',\n" +
                "                liteLoginUrl: '/IT/myoox/login/LiteLogin',\n" +
                "                forgotPassword: '/IT/myoox/ForgotPassword',\n" +
                "                registration: '/IT/myoox/register',\n" +
                "                login: '/IT/myoox/login',\n" +
                "                ordersList: '/IT/myoox/Orders'\n" +
                "            },\n" +
                "            legal: {\n" +
                "                company: '/IT/Legal',\n" +
                "                copyright: '/IT/Legal/Copyright'\n" +
                "            },\n" +
                "            cart: {\n" +
                "                cartIndex: '/IT/Checkout/Cart',\n" +
                "                cartError: '/IT/Checkout/Cart/Error',\n" +
                "                decItemQty: '/IT/Checkout/Cart/DecItemQty',\n" +
                "                incItemQty: '/IT/Checkout/Cart/IncItemQty',\n" +
                "                removeItem: '/IT/Checkout/Cart/RemoveItem',\n" +
                "                addToDBAndRemoveItem: '/IT/Checkout/Cart/AddToDreamboxAndRemoveItem',\n" +
                "                addToCart: '/IT/Checkout/Cart/AddToCart',\n" +
                "                setShippingMethod: '/IT/Checkout/Cart/SetShippingMethod',\n" +
                "                popupHoliday: '/IT/Checkout/Cart/PopupHoliday',\n" +
                "                popupPremium: '/IT/Checkout/Cart/PopupPremium',\n" +
                "                setDeliveryRequest: '/IT/Checkout/Cart/SetDeliveryRequest',\n" +
                "                setPaymentMethod: '/IT/Checkout/Cart/SetPaymentRequest',\n" +
                "                applyPromocode: '/IT/Checkout/Cart/ApplyCoupon',\n" +
                "                setGiftWrapping: '/IT/Checkout/Cart/SetGiftWrapping',\n" +
                "                setGiftWrappingOption: '/IT/Checkout/Cart/SetGiftWrappingOption',\n" +
                "                updateGiftWrappingKit: '/IT/Checkout/Cart/UpdateGiftWrappingKit',\n" +
                "                itemsForVoucher: '/IT/Checkout/Cart/ItemsForCoupon',\n" +
                "                resetGiftCertificateCode: '/IT/Checkout/Cart/ResetGiftCertificateCode',\n" +
                "                forgotPassword: '/IT/myoox/ForgotPassword',\n" +
                "                updateDeliveryBillingAddress: '/IT/Checkout/Delivery/UpdateDeliveryBillingCountry',\n" +
                "                selectShippingMyooxAddress: '/IT/Checkout/Delivery/SelectShippingMyooxAddress',\n" +
                "                selectBillingMyooxAddress: '/IT/Checkout/Delivery/SelectBillingMyooxAddress',\n" +
                "                checkZipcodeDeliveryGuaranteed: '/IT/Checkout/Delivery/CheckDeliveryAvailability',\n" +
                "                setShippingNotGuaranteed: '/IT/Checkout/Delivery/SetShippingNotGuaranteed',\n" +
                "                checkSession: '/IT/Checkout/CheckSession',\n" +
                "                checkSessionSubmit: '/IT/Checkout/CheckSession/CheckSessionSubmit',\n" +
                "                paymentCardOwnerDataForm: '/IT/Checkout/Payment/PaymentCardOwnerDataForm',\n" +
                "                orderDetails: '/IT/Checkout/OrderDetailsRecap',\n" +
                "                checkPersistentCart: '/IT/Checkout/Persistent/CheckPersistentCart',\n" +
                "                unsetPersistenMessages: '/IT/Checkout/Persistent/UnsetPersistentMessages',\n" +
                "                detailedRecap: '/IT/Checkout/CartRecap/DetailedRecap'\n" +
                "            },\n" +
                "            \n" +
                "            \n" +
                "            delivery: {\n" +
                "                UpdateSelectedKiala: '/IT/Checkout/DeliveryKiala/UpdateSelectedKiala',\n" +
                "                popupHoliday: '/IT/Checkout/Delivery/PopupHoliday',\n" +
                "                setDelivery: '/IT/Checkout/Delivery/SetDelivery',\n" +
                "                setDeliveryType: '/IT/Checkout/Delivery/SetDeliveryType',\n" +
                "                setGiftWrapping: '/IT/Checkout/Delivery/setGiftWrapping',\n" +
                "                getAvailableDeliveryOptions: '/IT/Checkout/Delivery/GetAvailableDeliveryOptions'\n" +
                "            },\n" +
                "\n" +
                "            layer14: {\n" +
                "                topCategories: '/TopFilters/MoreCategories?isoCode=IT'\n" +
                "            }\n" +
                "        },\n" +
                "\n" +
                "        cookieLaw: {\n" +
                "            text: 'Cookie Policy: questo sito utilizza i cookie. Se continui a navigare sul sito acconsenti alle Condizioni e ai Termini, alla Privacy Policy e utilizzo dei cookie durante la navigazione sul sito. Per saperne di pi&#249; sui cookie e su come cambiare le impostazioni, consulta la Cookie Policy',\n" +
                "            buttonLabel: 'OK'\n" +
                "        },\n" +
                "\n" +
                "        nav: {\n" +
                "            section: \"Help\",\n" +
                "            subSection: \"NoSection\",\n" +
                "            countryCode: \"IT\",\n" +
                "            dept: 'OrderDetails',\n" +
                "            currency: 'EUR',\n" +
                "            sectionsConfig: [\n" +
                "                        \n" +
                "                            { \n" +
                "                                sectionId: 'Home', \n" +
                "                                akamaiCookie: 'AKAMAI_HOME' \n" +
                "                            },\n" +
                "                        \n" +
                "                        \n" +
                "                            { \n" +
                "                                sectionId: 'Item', \n" +
                "                                akamaiCookie: 'AKAMAI_ITEM' \n" +
                "                            },\n" +
                "                        \n" +
                "                        \n" +
                "                            { \n" +
                "                                sectionId: 'SearchResult', \n" +
                "                                akamaiCookie: 'AKAMAI_SEARCHRESULT' \n" +
                "                            },\n" +
                "                        \n" +
                "                        \n" +
                "                            { \n" +
                "                                sectionId: 'DesignerIndex', \n" +
                "                                akamaiCookie: 'AKAMAI_DESIGNERINDEX' \n" +
                "                            },\n" +
                "                        \n" +
                "                        \n" +
                "                            { \n" +
                "                                sectionId: 'CategoryIndex', \n" +
                "                                akamaiCookie: 'AKAMAI_CATEGORYINDEX' \n" +
                "                            }\n" +
                "                        \n" +
                "\n" +
                "            ]\n" +
                "        },\n" +
                "\n" +
                "        utility: {\n" +
                "            isDevelopment: 'False',\n" +
                "            sessionTP: '',\n" +
                "            isMobile: 'False'\n" +
                "        },\n" +
                "\n" +
                "        labels: {\n" +
                "            persistentCart_info: \"Nella tua Shopping Bag troverai alcuni articoli che avevi scelto durante la tua ultima visita.\"\n" +
                "        },\n" +
                "\n" +
                "        codfisInfo: \"Con l’entrata in vigore del D.L. dell’11 dicembre 2012, n. 216 (art. 1.2 lett. f), è divenuto obbligatorio indicare il codice fiscale dell’acquirente nella fattura. Ti invitiamo pertanto a inserire nell’apposito campo il tuo codice fiscale corretto, così come riportato sul tuo tesserino. L’inserimento del codice fiscale è necessario per permetterci di emettere fattura.\",\n" +
                "\n" +
                "        analytics: {},\n" +
                "\n" +
                "        social: {\n" +
                "            fbAppId: \"225447960817285\",\n" +
                "            active: ['facebook', 'google', 'twitter', 'pinterest'],\n" +
                "            cancelUrl: '/IT/Common/ShareRedirectUrl'\n" +
                "        }\n" +
                "    };\n" +
                "\n" +
                "    if (!(Links)) {\n" +
                "        var Links = jsInit.links;\n" +
                "    }\n" +
                "</script>\n" +
                "    \n" +
                "<script type=\"text/javascript\" src=\"http://www.yoox.com/_js_/0/330/yoox/js/dist/js.frw.js\"></script>\n" +
                "    \n" +
                "    </head>\n" +
                "    <body  id=\"Help\" class=\"hideUserbarOnLoad\" >\n" +
                "        <div id=\"container\" class=\"OrderDetails IT\">\n" +
                "            \n" +
                "                <div id=\"userBar\" class=\"darkArea\" data-action-url=\"/IT/Userbar\">\n" +
                "\n" +
                "    <div id=\"switchCountryContainerLayer\" style=\"display: none;\" >\n" +
                "        <div class=\"fixedWidth\">\n" +
                "            <span class=\"sclClose\"></span>\n" +
                "            <span class=\"sclShippingTo\">We've selected shipping to</span>\n" +
                "            <span class=\"sclCurrentCountry fontBold\" style=\"background-image: url('http://cdn2.yoox.biz/yoox90/layout/flags/IT.jpg'); \">ITALY</span>\n" +
                "            <span class=\"sclForYou\">for you.</span>\n" +
                "            <span class=\"sclOk stdButton stdButtonGray fontBold fontUppercase\">OK</span>\n" +
                "            <span class=\"sclOr\">or</span>\n" +
                "            <span class=\"sclChangeCountry stdButton stdButtonGray fontBold fontUppercase\">Change country</span>\n" +
                "        </div>        \n" +
                "    </div>\n" +
                "\n" +
                "<div id=\"myooxNotLogged\" class=\"jsonPHeader socialLogin\">\n" +
                "    <div class=\"panelTopBar\">\n" +
                "        <div class=\"fixedWidth\">MYOOX</div>\n" +
                "    </div>\n" +
                "    <div class=\"fixedWidth\">\n" +
                "        <div id=\"UserBarRegister\" class=\"floatLeft\">\n" +
                "            <div id=\"registerPanel\" class=\"floatLeft\">\n" +
                "                <div id=\"myooxLogo\" class=\"fontSans floatLeft\">MYOOX</div>\n" +
                "\n" +
                "                <a id=\"newToMyoox\" href=\"/IT/myoox/register\" name=\"&amp;lid=layerRegister&amp;lpos=header\">\n" +
                "                    <span id=\"newToMyooxTitle\">Nuovo utente?</span>\n" +
                "                    <span id=\"newToMyooxRegister\">Registrati ora</span>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "\n" +
                "            <div id=\"changeCountry\" class=\"floatLeft\">\n" +
                "                <a id=\"currentCountry\" href=\"javascript:;\">\n" +
                "                    <img src=\"http://cdn2.yoox.biz/yoox90/layout/flags/IT.jpg\"  alt=\"IT\" class=\"myooxFlag\"/>\n" +
                "                    <span class=\"labels\">\n" +
                "                        <span>ITALY</span>\n" +
                "                        <span class=\"changeCountry\">Change</span>\n" +
                "                    </span>\n" +
                "                </a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div id=\"loginWrapper\" class=\"floatRight\"></div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"fixedWidth\">\n" +
                "        <div id=\"switchCountryContainer\">\n" +
                "        <span class=\"shippingTo\">Shipping to:</span>\n" +
                "        <a class=\"currentCountry fontBold\" data-ajax=\"true\" data-ajax-loading=\"#countries-modal-loading\" data-ajax-method=\"GET\" data-ajax-mode=\"replace\" data-ajax-success=\"switchShippingToSuccess\" data-ajax-update=\"#chooseCountryContainer\" href=\"/IT/Footer/Countries\" id=\"switchCountryButton\" name=\"&amp;lid=choose_country&amp;lpos=footer\" style=\"background-image: url(&#39;http://cdn2.yoox.biz/yoox90/layout/flags/IT.jpg&#39;);\">ITALY</a>\n" +
                "    </div>\n" +
                "\n" +
                "    <div id=\"toolbarMenu\">\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <a id=\"loginToolbar\" href=\"http://www.yoox.com/myoox/login\" name=\"&amp;lid=login&amp;lpos=header\" rel=\"nofollow\">\n" +
                "                    MYOOX&#160;<span class=\"login\">Login</span>\n" +
                "                </a>\n" +
                "            </li>\n" +
                "            <li class=\"yellow\">\n" +
                "                <a id=\"registerToolbar\" href=\"http://www.yoox.com/myoox/Register\" name=\"&amp;lid=register&amp;lpos=header\">\n" +
                "                    <span>Registrati</span>\n" +
                "                </a>\n" +
                "            </li>\n" +
                "            <li>\n" +
                "                <a id=\"customercareToolbar\" href=\"http://help.yoox.com\" name=\"&amp;lid=customercare&amp;lpos=header\" rel=\"nofollow\">\n" +
                "                    Servizio Clienti\n" +
                "                </a>\n" +
                "            </li>\n" +
                "            <li id=\"cartLink\" data-action-url=\"\">\n" +
                "                <a class=\"shoppingbag\" href=\"http://www.yoox.com/IT/Checkout/Cart\" name=\"&amp;lid=cart&amp;lpos=header\" rel=\"nofollow\">\n" +
                "                    <span>Shopping Bag\n" +
                "                        <span class=\"spriteUserbar shoppingBag\"></span>\n" +
                "                    </span>\n" +
                "                </a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<div id=\"cartLayer\" class=\"userBarLayer lightArea\">\n" +
                "    <div class=\"loading\"></div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "                </div>\n" +
                "            \n" +
                "\n" +
                "<div id=\"header\" class=\"IT\">\n" +
                "    <div class=\"fixedWidth\">\n" +
                "        <div id=\"areaMenuContainer\" class=\"navContainer\">\n" +
                "                <a id=\"logo\" class=\"logoYOOX\" href=\"http://www.yoox.com/IT\" name=\"&lid=logo&lpos=header\">YOOX.COM</a>\n" +
                "            <div id=\"areaMenu\">\n" +
                "                    <ul id=\"fashionMenu\" class=\"floatLeft selectedArea\">\n" +
                "                        <li class=\"areaItem floatLeft fontUppercase\" >\n" +
                "                            <a href=\"/it/donna\" name=\"&lid=fashion&lpos=header\">Fashion</a>\n" +
                "                        </li>\n" +
                "                                <li class=\"subAreaItem floatLeft\">\n" +
                "                                    <a href=\"/it/donna\" name=\"&lid=women&lpos=header\">Donna</a>\n" +
                "                                </li>\n" +
                "                                <li class=\"subAreaItem floatLeft\">\n" +
                "                                    <a href=\"/it/uomo\" name=\"&lid=men&lpos=header\">Uomo</a>\n" +
                "                                </li>\n" +
                "                                <li class=\"subAreaItem floatLeft\">\n" +
                "                                    <a href=\"/it/bambini/collezioni/subhome\" name=\"&lid=kids&lpos=header\">Bambino</a>\n" +
                "                                </li>\n" +
                "                    </ul>\n" +
                "                    <ul id=\"designMenu\" class=\"floatLeft\">\n" +
                "                        <li class=\"areaItem floatLeft fontUppercase\" >\n" +
                "                            <a href=\"/it/design\" name=\"&lid=design&lpos=header\">Design</a>\n" +
                "                        </li>\n" +
                "                    </ul>\n" +
                "                    <ul id=\"artMenu\" class=\"floatLeft\">\n" +
                "                        <li class=\"areaItem floatLeft fontUppercase\" >\n" +
                "                            <a href=\"/it/art\" name=\"&lid=art&lpos=header\">Art</a>\n" +
                "                        </li>\n" +
                "                    </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "    </div>\n" +
                "</div>\n" +
                "            <div id=\"mainContainer\" class=\"fixedWidth\">\n" +
                "                \n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"mainContent\">\n" +
                "    <h1 class=\"fontBold fontUppercase\">Segui il tuo ordine</h1>\n" +
                "    <div id=\"boxContent\" class=\"ui-helper-clearfix\">\n" +
                "        <div id=\"orderDetailForm\" class=\"floatLeft\">\n" +
                "<form action=\"/help/orders\" class=\"myForm editor-3cols leftPadding\" id=\"frm_order_detail\" method=\"get\">    <fieldset>\n" +
                "        <legend>Vedi la lista degli articoli che hai ordinato e lo stato della spedizione. <br/>Se hai appena concluso il tuo ordine, potrai vedere i dettagli nel giro di qualche minuto.</legend>\n" +
                "        <div class=\"appform custom\">\n" +
                "            <div class=\"updatepanel\">\n" +
                "                <div class=\"editor-row\">\n" +
                "                    <div class=\"editor-col\">\n" +
                "                        <label for=\"OrderNumber\">Inserisci il tuo Numero d&#39;ordine</label>\n" +
                "                        <div class=\"editor-field floatLeft\">\n" +
                "                            <div class=\"editor-input\">\n" +
                "                                <input class=\"uniform text \" data-val=\"true\" data-val-length=\"I campi indicati non sono stati compilati correttamente\" data-val-length-max=\"16\" data-val-length-min=\"14\" data-val-required=\"I campi indicati non sono stati compilati correttamente\" id=\"OrderNumber\" maxlength=\"14\" name=\"OrderNumber\" type=\"text\" value=\"2310Y5AC115029\" />\n" +
                "                                <span class=\"field-validation-valid\" data-valmsg-for=\"OrderNumber\" data-valmsg-replace=\"true\"></span>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"editor-buttons floatLeft\">\n" +
                "                            <input type=\"submit\" class=\"stdButton stdButtonDarkGray fontUppercase floatLeft\" value=\"INVIA\" />\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </fieldset>\n" +
                "</form>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"orderDetail\">\n" +
                "    <div class=\"orderInfoBox ui-helper-clearfix Spedito\">\n" +
                "        <div class=\"orderUpperBox\"></div>\n" +
                "        <div class=\"orderTop noBorderTop\">\n" +
                "            <div class=\"leftColumn\">\n" +
                "                    <h3>Il tuo ordine è stato spedito il 2015-10-23.</h3> \n" +
                "                Se il tuo ordine è stato appena spedito vedrai le informazioni aggiornate entro qualche ora.\n" +
                "            </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"orderDetails\">\n" +
                "            <div class=\"infoArticle\">\n" +
                "                <div class=\"orderDetailsRow orderDetailsTitle\">\n" +
                "                    <div class=\"infoArticleCol infoArticleFirstCol solidBottom\">ARTICOLO</div>\n" +
                "                    <div class=\"infoArticleCol infoArticleColorCol solidBottom\">COLORE</div>\n" +
                "                    <div class=\"infoArticleCol infoArticleSizeCol solidBottom\">TAGLIA</div>\n" +
                "                    <div class=\"infoArticleCol infoArticleQtyCol solidBottom\">QUANTITÀ</div>\n" +
                "                    <div class=\"infoArticleCol infoArticlePriceCol solidBottom\">PREZZO</div>\n" +
                "                </div>\n" +
                "                \n" +
                "                    <div class=\"itemList orderDetailsRow solidBottom\">\n" +
                "                        <div class=\"infoArticleCol infoArticleFirstCol\">\n" +
                "    <div class=\"itemImg\">\n" +
                "        <img src=\"http://images.yoox.com/37/37647512dd_8_f.jpg\" alt=\"MELTIN POT\"/>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"itemShortInfo\">\n" +
                "        <a href=\"/it/37647512DD/item#sts=orders80\">\n" +
                "            <div>\n" +
                "                <span class=\"fontBold itemShortBrand\">\n" +
                "                    MELTIN POT\n" +
                "                </span>\n" +
                "\n" +
                "                <br />\n" +
                "                T-shirts\n" +
                "\n" +
                "            </div>\n" +
                "        </a>\n" +
                "        <div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"infoArticleCol\">\n" +
                "    <div class=\"itemShortInfo\">\n" +
                "        \n" +
                "Blu    </div>\n" +
                "</div>\n" +
                "<div class=\"infoArticleCol infoArticleSizeCol\">\n" +
                "    <div class=\"itemShortInfo\">\n" +
                "XL (Taglia internazionale)            <br />\n" +
                "    </div>\n" +
                "</div>\n" +
                "<div class=\"infoArticleCol infoArticleQtyCol\">\n" +
                "    <div class=\"itemShortInfo\">\n" +
                "        1\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"infoArticleCol infoArticlePriceCol\">\n" +
                "    \n" +
                "    <div class=\"itemShortInfo\">\n" +
                "        <div class=\"price fontBold\" >EUR 13,00</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "                    </div>\n" +
                "                    <div class=\"itemList orderDetailsRow solidBottom\">\n" +
                "                        <div class=\"infoArticleCol infoArticleFirstCol\">\n" +
                "    <div class=\"itemImg\">\n" +
                "        <img src=\"http://images.yoox.com/37/37647512tk_8_f.jpg\" alt=\"MELTIN POT\"/>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"itemShortInfo\">\n" +
                "        <a href=\"/it/37647512TK/item#sts=orders80\">\n" +
                "            <div>\n" +
                "                <span class=\"fontBold itemShortBrand\">\n" +
                "                    MELTIN POT\n" +
                "                </span>\n" +
                "\n" +
                "                <br />\n" +
                "                T-shirts\n" +
                "\n" +
                "            </div>\n" +
                "        </a>\n" +
                "        <div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"infoArticleCol\">\n" +
                "    <div class=\"itemShortInfo\">\n" +
                "        \n" +
                "Grigio chiaro    </div>\n" +
                "</div>\n" +
                "<div class=\"infoArticleCol infoArticleSizeCol\">\n" +
                "    <div class=\"itemShortInfo\">\n" +
                "XL (Taglia internazionale)            <br />\n" +
                "    </div>\n" +
                "</div>\n" +
                "<div class=\"infoArticleCol infoArticleQtyCol\">\n" +
                "    <div class=\"itemShortInfo\">\n" +
                "        1\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"infoArticleCol infoArticlePriceCol\">\n" +
                "    \n" +
                "    <div class=\"itemShortInfo\">\n" +
                "        <div class=\"price fontBold\" >EUR 13,00</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "                    </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div id=\"deliveryAddressOrder\" class=\"solidBottom\">\n" +
                "                <div id=\"deliveryAddressOrderWrapper\">\n" +
                "                    <span class=\"fontUppercase\">Indirizzo di spedizione</span>\n" +
                "\n" +
                "                        <p>Cju b</p>\n" +
                "\n" +
                "                        <p>Bbnnbb</p>\n" +
                "\n" +
                "                        <p>Italy</p>\n" +
                "\n" +
                "                        <p>12345</p>\n" +
                "\n" +
                "\n" +
                "                </div>\n" +
                "\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"infoCostsContainer ui-helper-clearfix\">\n" +
                "                <div class=\"infoCosts\">\n" +
                "\n" +
                "                    <div class=\"orderDetailsRow total\">\n" +
                "                        <div class=\"cost\">EUR 26,00</div>\n" +
                "                        <div class=\"costtype leftColumn\">Totale articoli</div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "                    <div class=\"orderDetailsRow\">\n" +
                "                        <div class=\"cost\">\n" +
                "                            EUR 0,00\n" +
                "                        </div>\n" +
                "                        <div class=\"desc1 leftColumn\">TIPO DI SPEDIZIONE - Standard</div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                        <div class=\"orderDetailsRow\">\n" +
                "                            <div class=\"desc1 leftColumn\">TIPO DI PAGAMENTO - \n" +
                "                                 <span>\n" +
                "Contrassegno                                 </span>\n" +
                "                            </div>\n" +
                "                            <div class=\"cost\">\n" +
                "                                EUR 4,00\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "\n" +
                "\n" +
                "                    \n" +
                "                    <div class=\"orderDetailsRow total orderTotal fontBold\">\n" +
                "                        <div class=\"cost\">EUR 30,00</div>\n" +
                "                        <div class=\"costtype leftColumn\">TOTALE</div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"closeButton closeDetails solidTop\">\n" +
                "                <a href=\"javscript:$.noop();\" class=\"closeaccordion fontUppercase fontBold\">Chiudi</a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "<script type=\"text/javascript\">\n" +
                "    jsInit.nav.subSection = '';\n" +
                "</script>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "            </div>\n" +
                "            \n" +
                "<div id=\"footer\" class=\"darkArea fontUppercase\">\n" +
                "    <div id=\"footerContainer\">\n" +
                "        <div id=\"footerTop\">\n" +
                "            <div class=\"fixedWidth ui-helper-clearfix\">\n" +
                "\n" +
                "                <div class=\"footerYooxnews floatLeft\">\n" +
                "                    <div class=\"footerTitle\">\n" +
                "                        <span class=\"yooxCom\"></span>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "\n" +
                "                <div id=\"footerNews\" class=\"floatLeft\">\n" +
                "                    <div id=\"footerNewsContainer\">\n" +
                "<form action=\"/IT/Footer/RegisterUserToNewsletter\" data-ajax=\"true\" data-ajax-method=\"GET\" data-ajax-mode=\"replace\" data-ajax-timeout=\"5000\" data-ajax-update=\"#footerNewsContainer\" id=\"fastMail\" method=\"post\" name=\"fastMail\" novalidate=\"novalidate\"><span class=\"field-validation-valid\" data-valmsg-for=\"Mail\" data-valmsg-replace=\"true\"></span><span class=\"field-validation-valid\" data-valmsg-for=\"Gender\" data-valmsg-replace=\"true\"></span>    <div class=\"appform\">\n" +
                "        <div id=\"newsLetter\" class=\"editor-input\">\n" +
                "            <div class=\"newsletterForm\">\n" +
                "                <div class=\"newsletterFormWrap\">\n" +
                "                    <input autocomplete=\"off\" class=\"text\" data-val=\"true\" data-val-email=\"Indirizzo e-mail non valido\" data-val-required=\"Inserisci l&#39;indirizzo e-mail\" id=\"fastMailEmail\" name=\"Mail\" novalidate=\"novalidate\" placeholder=\"Inserisci la tua e-mail\" tabindex=\"0\" type=\"email\" value=\"\" />\n" +
                "                    <input type=\"submit\" id=\"newsletterSubmit\" class=\"stdButton stdButtonGray fontBold fontUppercase\" name = \"&amp;lid=send_newsletter&amp;lpos=central_bottom\"  value=\"Invia\"/>\n" +
                "                    <div class=\"clear\"></div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div id=\"chooseSex\">\n" +
                "                <input checked=\"checked\" id=\"chooseD\" name=\"Gender\" type=\"radio\" value=\"D\" /><label for=\"chooseD\">Donna</label>\n" +
                "                <input id=\"chooseU\" name=\"Gender\" type=\"radio\" value=\"U\" /><label for=\"chooseU\">Uomo</label>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        $(document).ready(function () {\n" +
                "            $Y.utils.makeMeUniform('#fastMail', {\n" +
                "                \"input\": { types: [{ name: \"hidden\", allowed: false }, { name: \"submit\", allowed: false }, { name: \"button\", allowed: false }, { name: \"text\", allowed: false }, { name: \"email\", allowed: false }] }\n" +
                "            });\n" +
                "        });\n" +
                "    </script>\n" +
                "</form>\n" +
                "                        <div class=\"clear\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class=\"footerYooxnews floatRight\">\n" +
                "                    <div class=\"fashionUpdates\">Ricevi le ultime novità e promozioni.<br/>Iscriviti alla newsletter</div>\n" +
                "                </div>\n" +
                "\n" +
                "            </div>\n" +
                "\n" +
                "            <div id=\"footerSocialContainer\" class=\"ui-helper-clearfix\">\n" +
                "                <div class=\"fixedWidth\">\n" +
                "                    <div id=\"footerSocialContainerWrapper\">\n" +
                "\n" +
                "                        <div id=\"footerFollowUs\" class=\"floatLeft\">\n" +
                "                            <span class=\"floatLeft fontUppercase\">SEGUICI SU</span>\n" +
                "                                <ul>\n" +
                "                                    <li class=\"icoFooterSocial\" id=\"fbIco\">\n" +
                "                                        <a href=\"http://www.facebook.com/yoox\" rel=\"nofollow\" target=\"_blank\" name=\"&lid=facebook&lpos=footer_social\"></a>\n" +
                "                                    </li>\n" +
                "\n" +
                "                                    <li class=\"icoFooterSocial\" id=\"twIco\">\n" +
                "                                        <a rel=\"nofollow\" href=\"http://twitter.com/yoox\" \n" +
                "                                       target=\"_blank\" name=\"&lid=twitter&lpos=footer_social\"></a>\n" +
                "                                    </li>\n" +
                "\n" +
                "                                    <li class=\"icoFooterSocial\" id=\"instIco\">\n" +
                "                                        <a rel=\"nofollow\" href=\"https://instagram.com/yoox\" target=\"_blank\" name=\"&lid=instagram&lpos=footer_social\"></a>\n" +
                "                                    </li>\n" +
                "\n" +
                "                                    <li class=\"icoFooterSocial\" id=\"ytIco\">\n" +
                "                                        <a rel=\"nofollow\" href=\"http://www.youtube.com/yoox\" target=\"_blank\" name=\"&lid=youtube&lpos=footer_social\"></a>\n" +
                "                                    </li>\n" +
                "\n" +
                "                                    <li class=\"icoFooterSocial\" id=\"piIco\">\n" +
                "                                        <a rel=\"nofollow\" href=\"http://pinterest.com/yoox/\" target=\"_blank\" name=\"&lid=pinterest&lpos=footer_social\"></a>\n" +
                "                                    </li>\n" +
                "\n" +
                "                                    <li class=\"icoFooterSocial\" id=\"gpIco\">\n" +
                "                                        <a rel=\"nofollow\" href=\"https://plus.google.com/+yoox\" target=\"_blank\" name=\"&lid=google_plus&lpos=footer_social\"></a>\n" +
                "                                    </li>\n" +
                "                                    \n" +
                "                                    <li class=\"icoFooterSocial\" id=\"wcIco\">\n" +
                "                                        <a rel=\"nofollow\" href=\"/it/project/yoox_on_wechat\" name=\"&lid=wechat&lpos=footer_social\" target=\"_blank\"></a>\n" +
                "                                    </li>\n" +
                "                                </ul>\n" +
                "\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div id=\"footerLink\" class=\"fixedWidth\">\n" +
                "            <div id=\"footerMain\" class=\"footerColumn\">\n" +
                "                <div class=\"wrap\">\n" +
                "                    <span class=\"footerTitle fontSans\">NEW TO YOOX.COM</span>\n" +
                "                    <ul>\n" +
                "                        <li><a name=\"&lid=shopping_guide&lpos=footer_new\" rel=\"nofollow\" data-name=\"footer1_sub1\" href=\"http://help.yoox.com/system/web/custom/hp/topic.jsp?topicId=1025\">Shopping guide</a></li>\n" +
                "                            <li><a name=\"&lid=mobile&lpos=footer_new\" rel=\"nofollow\" data-name=\"footer1_sub2\" href=\"/project/mobile_apps\">iPhone/iPad/Android</a></li>   \n" +
                "                        <li><a name=\"&lid=designer_index&lpos=footer_new\" data-name=\"footer1_sub3\" href=\"/it/designerIndex\">Vedi tutti i designer</a></li>\n" +
                "                        <li><a name=\"&lid=category_index&lpos=footer_new\" data-name=\"footer1_sub4\" href=\"/it/categoryIndex\">Vedi tutte le categorie</a></li>\n" +
                "                        \n" +
                "\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div id=\"footerHelp\" class=\"footerColumn\">\n" +
                "                <div class=\"wrap\">\n" +
                "                    <span class=\"footerTitle fontSans\">HELP</span>\n" +
                "<ul>\n" +
                "    <li>\n" +
                "        <a data-ajax=\"true\" data-ajax-loading=\"#ShippingInfo-modal-loading\" data-ajax-method=\"Get\" data-ajax-mode=\"before\" data-ajax-success=\"shippingInfoRequestModal\" data-ajax-update=\"#container\" data-name=\"footer2_sub1\" href=\"/shipping/ShippingInfo\" name=\"&amp;lid=shipping_info&amp;lpos=footer_help\" rel=\"nofollow\">Tempi e costi di spedizione</a>\n" +
                "        <div id=\"ShippingInfo-modal-loading\" class=\"avoidUserTextSelection loading\">\n" +
                "            &nbsp;\n" +
                "        </div>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        <a data-ajax=\"true\" data-ajax-loading=\"#securePayments-modal-loading\" data-ajax-method=\"Get\" data-ajax-mode=\"before\" data-ajax-success=\"securePaymentsRequestModal\" data-ajax-update=\"#container\" data-name=\"footer2_sub2\" href=\"/IT/Footer/SecurePayments?Length=6\" name=\"&amp;lid=payments &amp;lpos=footer_help\" rel=\"nofollow\">Pagamenti sicuri</a>\n" +
                "        <div id=\"securePayments-modal-loading\" class=\"avoidUserTextSelection loading\">\n" +
                "            &nbsp;\n" +
                "        </div>\n" +
                "    </li>\n" +
                "    <li><a name=\"&lid=product_quality&lpos=footer_help\" data-name=\"footer2_sub7\" href=\"http://help.yoox.com/system/web/custom/hp/article.jsp?articleId=4095\">Qualità del prodotto</a></li>\n" +
                "    <li><a name=\"&lid=order_tracking&lpos=footer_help\" data-name=\"footer2_sub3\" rel=\"nofollow\" href=\"http://www.yoox.com/help/orders\">Segui il tuo ordine</a></li>\n" +
                "    <li>\n" +
                "        <a data-ajax=\"true\" data-ajax-loading=\"#secureInfo-modal-loading\" data-ajax-method=\"Get\" data-ajax-mode=\"before\" data-ajax-success=\"returnInfoRequestModal\" data-ajax-update=\"#container\" data-name=\"footer2_sub4\" href=\"/IT/Footer/ReturnsInfo?Length=6\" name=\"&amp;lid=returns&amp;lpos=footer_help\" rel=\"nofollow\">Resi e rimborsi</a>\n" +
                "        <div id=\"secureInfo-modal-loading\" class=\"avoidUserTextSelection loading\">\n" +
                "            &nbsp;\n" +
                "        </div>\n" +
                "    </li>\n" +
                "    <li><a name=\"&lid=faq&lpos=footer_help\" data-name=\"footer2_sub5\" rel=\"nofollow\" href=\"http://help.yoox.com\">FAQ</a></li>\n" +
                "    <li><a name=\"&lid=size_guide&lpos=footer_help\" data-name=\"footer2_sub6\" rel=\"nofollow\" href=\"http://help.yoox.com/system/web/custom/hp/topic.jsp?topicId=1027\">Tabella taglie</a></li>\n" +
                "</ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div id=\"footerMyoox\" class=\"footerColumn\">\n" +
                "                <div class=\"wrap\">\n" +
                "                    <span class=\"footerTitle fontSans\">MYOOX</span>\n" +
                "                    <ul>\n" +
                "                        <li><a name=\"&lid=myoox_home&lpos=footer_myoox\" data-name=\"footer3_sub1)\" rel=\"nofollow\" href=\"http://www.yoox.com/myoox\">Login</a></li>\n" +
                "                        <li><a name=\"&lid=order_tracking&lpos=footer_myoox\" data-name=\"footer3_sub3\" rel=\"nofollow\" href=\"http://www.yoox.com/myoox/orders\">I miei ordini</a></li>\n" +
                "                        <li><a name=\"&lid=my_account&lpos=footer_myoox\" rel=\"nofollow\" data-name=\"footer3_sub4\" href=\"http://www.yoox.com/myoox/account\">I miei dati</a></li>\n" +
                "                        <li><a name=\"&lid=dream_box&lpos=footer_myoox\" rel=\"nofollow\" data-name=\"footer3_sub5\" href=\"http://www.yoox.com/myoox/dreambox\">Dream Box</a></li>\n" +
                "                        <li><a name=\"&lid=premiere&lpos=footer_myoox\" rel=\"nofollow\" data-name=\"footer3_sub6\" href=\"http://www.yoox.com/myoox/premiere\">Première</a></li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div id=\"footerAbout\" class=\"footerColumn\">\n" +
                "                <div class=\"wrap\">\n" +
                "                    <span class=\"footerTitle fontSans\">ABOUT US</span>\n" +
                "                    <ul>\n" +
                "                        <li><a name=\"&lid=about&lpos=footer_about\" data-name=\"footer4_sub1\" href=\"http://www.ynap.com/it/store/yoox-com/\" target=\"_blank\">Chi siamo</a></li>\n" +
                "                        <li><a name=\"&lid=press&lpos=footer_about\" data-name=\"footer4_sub2\" href=\"http://www.ynap.com/it/pages/newsroom/\" target=\"_blank\">Press</a></li>\n" +
                "                            <li><a name=\"&lid=affiliation&lpos=footer_about\" rel=\"nofollow\" data-name=\"footer4_sub3\" href=\"http://www.yoox.com/cms/affiliation/affiliation.asp\">Affiliazione</a></li>\n" +
                "                        <li><a name=\"&lid=careers&lpos=footer_about\" data-name=\"footer4_sub4\" href=\"http://www.ynap.com/it/pages/people/\" target=\"_blank\">Careers</a></li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "            <div id=\"footerCYCContainer\">\n" +
                "                <div id=\"chooseCountryContainer\">\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        <div id=\"footerCopyright\" class=\"fontUppercase\">\n" +
                "    <div class=\"fixedWidth\">\n" +
                "        <div id=\"copyContainer\" class=\"floatLeft\"><a href=\"http://www.ynap.com/it/pages/about-us/what-we-do/\" TARGET=\"_blank\">Powered by YOOX NET-A-PORTER GROUP</a> - <a id=\"copyright\" href=\"javascript:;\">Copyright</a> © 2000-2015 <a id=\"yooxspa\" href=\"javascript:;\">YOOX NET-A-PORTER GROUP S.p.A.</a> - All Rights Reserved - Licenza SIAE n. 401/I/526</div>\n" +
                "\n" +
                "        <div id=\"legalPrivacy\" class=\"floatRight\">\n" +
                "                <a href=\"http://www.yoox.com/cms/legal/cookiepolicy.asp\" rel=\"nofollow\" name=\"&amp;lid=privacy&amp;lpos=footer\">Cookie Policy</a>\n" +
                "            <a href=\"http://www.yoox.com/cms/legal/saleterms.asp\" rel=\"nofollow\" name=\"&amp;lid=legal&amp;lpos=footer\">Area Legale</a>\n" +
                "            <a href=\"http://www.yoox.com/cms/legal/privacypolicy.asp\" rel=\"nofollow\" name=\"&amp;lid=privacy&amp;lpos=footer\">Privacy</a>\n" +
                "        </div>\n" +
                "        <div class=\"clear\"></div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "    </div>\n" +
                "</div>        </div>\n" +
                "\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    var idPartner = $Y.cookie.get(\"SESSIONS\", \"ID_PARTNER\");\n" +
                "    var sessionTP = 0;\n" +
                "    if (idPartner === null && jsInit.utility.sessionTP !== \"\") {\n" +
                "        sessionTP = jsInit.utility.sessionTP || 0;\n" +
                "    } else {\n" +
                "        sessionTP = (idPartner !== null) ? idPartner.split('|')[1] : 0;\n" +
                "    }\n" +
                "    //****************************************************************//\n" +
                "    //TAG Commander Variables stuffing \n" +
                "    if (typeof tc_vars === \"undefined\") {\n" +
                "        var tc_vars = new Array();\n" +
                "    }\n" +
                "    //Environment\n" +
                "    tc_vars[\"env_template\"] = \"genericpage\";\n" +
                "    tc_vars[\"env_work\"] = \"PROD\";\n" +
                "    tc_vars[\"env_dnt\"] = \"disabled\";\n" +
                "    tc_vars[\"env_ftpPath\"] = \"http://cdn2.yoox.biz/yoox/tagCommander/prod/\";\n" +
                "   \n" +
                "    //Navigation\n" +
                "    tc_vars[\"nav_section\"] = \"Help\";\n" +
                "    tc_vars[\"nav_subsection\"] = \"OrderDetails\";\n" +
                "    tc_vars[\"nav_countryCode\"] = \"IT\";\n" +
                "    tc_vars[\"nav_countryId\"] = \"2\";\n" +
                "    tc_vars[\"nav_marketId\"] = \"2\";\n" +
                "    tc_vars[\"nav_languageCode\"] = \"IT\";\n" +
                "    tc_vars[\"nav_languageId\"] = \"2\";\n" +
                "    tc_vars[\"nav_currency\"] = \"EUR\";\n" +
                "    tc_vars[\"nav_sessionTP\"] = sessionTP;\n" +
                "    tc_vars[\"nav_historicalTP\"] = \"\";\n" +
                "    tc_vars[\"nav_abtest\"] = \"\"; // NO ABTest\n" +
                "    tc_vars[\"nav_dept\"] = \"OrderDetails\";\n" +
                "    tc_vars[\"nav_gender\"] = \"D\";\n" +
                "    tc_vars[\"nav_season\"] = \"E\";\n" +
                "    tc_vars[\"nav_releaseNo\"] = \"YOOX_10\";\n" +
                "    tc_vars[\"nav_page\"] = \"OrderDetails:Help\";\n" +
                "    tc_vars[\"nav_pagetype\"] = \"Help\";\n" +
                "    //tc_vars[\"nav_totalpageview\"] = \"\";\n" +
                "    \n" +
                "    //User\n" +
                "    //tc_vars[\"user_clustering\"] = \"\";\n" +
                "    tc_vars[\"user_gender\"] = \"D\";\n" +
                "    \n" +
                "    //tc_vars[\"user_totalorderslasttwelvemonths\"] = \"\";\n" +
                "    //tc_vars[\"user_daysfromlastorder\"] = \"\";\n" +
                "    //tc_vars[\"user_aov\"] = \"\";\n" +
                "    //tc_vars[\"user_aovlasttwelvemonths\"] = \"\";\n" +
                "    //tc_vars[\"user_aovlastorder\"] = \"\";\n" +
                "    //tc_vars[\"user_listbrands\"] = new Array();\n" +
                "    //tc_vars[\"user_listbrandslasttwelvemonths\"] = new Array();\n" +
                "    //tc_vars[\"user_listbrandslastorder\"] = new Array();\n" +
                "    //tc_vars[\"user_listcategories\"] = new Array();\n" +
                "    //tc_vars[\"user_listcategorieslasttwelvemonths\"] = new Array();\n" +
                "    //tc_vars[\"user_listcategorieslastorder\"] = new Array();\n" +
                "    \n" +
                "    //****************************************************************//\n" +
                "    //Classic Analytics jsInit stuffing \n" +
                "\n" +
                "    jsInit.analytics = {\n" +
                "        ga : {\n" +
                "            customvars : {\n" +
                "                siteType: {\n" +
                "                    index: 6,\n" +
                "                    name: \"siteType\",\n" +
                "                    value:\"website\",\n" +
                "                    opt_scope:2\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        sc: {\n" +
                "            pageName:\"OrderDetails:Help\",\n" +
                "            channel:\"OrderDetails\",\n" +
                "            prop39:\"IT\",\n" +
                "            prop15:\"2\",\n" +
                "            prop16:\"IT:OrderDetails:Help\",\n" +
                "            prop37:\"E\",\n" +
                "\t            prop17: \"OrderDetails\",\n" +
                "            prop18:\"none\",\n" +
                "            prop19:\"OrderDetails:none\",\n" +
                "            prop20:\"Help\",\n" +
                "            prop10:$Y.utils.isLogged() ? \"1\" : \"0\",\n" +
                "            prop43:\"Help\",\n" +
                "            prop44:\"OrderDetails\",\n" +
                "            prop11:\"IT:Help:OrderDetails\",\n" +
                "            prop55: (typeof (s_getLoadTime) === 'function' ? s_getLoadTime() : \"0\"),\n" +
                "            prop56: $(document).attr('title'),\n" +
                "            prop46: \"\",\n" +
                "            eVar46: \"\",\n" +
                "            eVar6: \"D\",\n" +
                "            campaign: sessionTP,\n" +
                "            prop59: \"\"\n" +
                "        },\n" +
                "        scUrl: \"/_js_/0/9/yoox/js/vendor/s_code.js\"\n" +
                "    };\n" +
                "</script>\n" +
                "\n" +
                "        \n" +
                "        \n" +
                "        <script type=\"text/javascript\">\n" +
                "            var google_conversion_id = 1071896576;\n" +
                "            var google_conversion_language = \"en\";\n" +
                "            var google_conversion_format = \"3\";\n" +
                "            var google_conversion_color = \"666666\";\n" +
                "            var google_conversion_label = \"\";\n" +
                "            var google_conversion_value = 0;\n" +
                "        </script>\n" +
                "        <div style=\"display: none\">\n" +
                "            <script type=\"text/javascript\" src=\"http://www.googleadservices.com/pagead/conversion.js\"></script>\n" +
                "        </div>\n" +
                "        <noscript>\n" +
                "            <div style=\"display: inline;\">\n" +
                "                <img height=\"1\" width=\"1\" style=\"border-style: none;\" alt=\"\" src=\"http://www.googleadservices.com/pagead/conversion/1066549713/?label=pcmACM_S3wEQ0YPJ_AM&amp;guid=ON&amp;script=0\" />\n" +
                "            </div>\n" +
                "        </noscript>\n" +
                "\n" +
                "        \n" +
                "<script type=\"text/javascript\" src=\"http://www.yoox.com/_js_/0/198/yoox/js/dist/corelib.js\"></script>    </body>\n" +
                "</html>";
    }
}