from scrapy.spiders import Spider
from scrapy.selector import Selector
from scrapy.exceptions import CloseSpider
from scrapy.http import Request

from marta.items import MartaItem


class PreciosaSpider(Spider):
 
    name = "preciosa"
    allowed_domains = ["preciosa-ornela.com"]
    start_urls = [     
                "http://preciosa-ornela.com/en/products/828-pastel-coating?highlight=WyJjb2F0aW5nIl0=",

                         
    ]
    

    def parse(self, response):

        sel = Selector(response)
        brands  = sel.xpath('//tr/td[contains(@class, "al")]/a[contains(@class, "nazwa")]')

        for brand in brands: 
           item = MartaItem()
           item_details_url = "http://www.kadoro.pl/" + brand.xpath('@href')[0].extract()
           item['url'] = item_details_url
           item['name'] = brand.xpath('text()').extract()
           yield Request(item_details_url, self.parse_item, meta={'item': item})

    
    def parse_item(self, response):               

        item = response.meta['item']        
        item['category'] = response.css('#produkt a:nth-child(1)::text').extract()
        item['subcategory'] = response.css('#produkt a+ a::text').extract()
        item['type'] = response.css('p a~ a+ a::text').extract()
        item['rozmiar'] = response.css('#produkt a:nth-child(17)::text').extract()
        item['id'] = response.css('#produkt p:nth-child(3)::text').extract()
        item['image'] = response.css('#produkt .im::attr(src)').extract()
        
        return item
      

    
