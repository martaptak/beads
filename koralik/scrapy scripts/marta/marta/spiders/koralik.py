from scrapy.spiders import Spider
from scrapy.selector import Selector
from scrapy.exceptions import CloseSpider
from scrapy.http import Request

from marta.items import MartaItem


class KoralikSpider(Spider):
 
    name = "koralik"
    allowed_domains = ["czechbeads.com"]
    start_urls = [     
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=112&p=0",  
                       
    ]

from scrapy.spiders import Spider
from scrapy.selector import Selector
from scrapy.exceptions import CloseSpider
from scrapy.http import Request

from marta.items import MartaItem


class KoralikSpider(Spider):
 
    name = "koralik"
    allowed_domains = ["czechbeads.com"]
    start_urls = [     
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=112&p=0",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=105&p=0",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=103",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=124",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=104",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=117&p=0",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=122&p=0",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=102&p=0",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=108&p=0",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=115&p=0",
                     "https://www.czechbeads.com/Catalog/Default.aspx?cid=118",
                          
    ]
    

    def parse(self, response):
    
        sel = Selector(response)
    
        brands = sel.xpath('//a[@class="CATITEMDESC"]')
        
        for brand in brands:                              
            url = brand.xpath('@href')[0].extract()            
            request = Request("https://www.czechbeads.com/Catalog/"+url, self.parse_shape)            
            yield request

    def parse_shape(self, response):
        
        sel = Selector(response)
    
        shapes = sel.xpath('//span[@class="CATITEM"]/a')
        
        for shape in shapes:                              
            url = shape.xpath('@href')[0].extract()            
            request = Request("https://www.czechbeads.com/Catalog/"+url, self.parse_item)            
            yield request


    def parse_item(self, response):               

        item = MartaItem()
        item['id'] = response.xpath('//span[@id="_ctl0__ctl0_cphMainBody_cphMainBody_lblItemCode"]/text()').extract()
        item['name'] = response.xpath('//title/text()').extract()
        item['category'] = response.xpath('//span[@id="_ctl0__ctl0_cphMainBody_cphMainBody_lblItemText"]/a[1]/text()').extract()
        item['subcategory'] = response.xpath('//span[@id="_ctl0__ctl0_cphMainBody_cphMainBody_lblItemText"]/a[2]/text()').extract()
        item['image'] = "https://www.czechbeads.com/" + response.xpath('//img[@id="_ctl0__ctl0_cphMainBody_cphMainBody_imgProduct"]/@src').extract()[0]   
        return item


      

    
