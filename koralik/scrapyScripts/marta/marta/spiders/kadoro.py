from scrapy.spiders import Spider
from scrapy.selector import Selector
from scrapy.exceptions import CloseSpider
from scrapy.http import Request

from marta.items import MartaItem


class KadoroSpider(Spider):
 
    name = "kadoro"
    allowed_domains = ["kadoro.pl"]
    start_urls = [     
                     "http://www.kadoro.pl/mapa-serwisu-a.php",
                     "http://www.kadoro.pl/mapa-serwisu-b.php",
                     "http://www.kadoro.pl/mapa-serwisu-c.php",
                     "http://www.kadoro.pl/mapa-serwisu-d.php",
                     "http://www.kadoro.pl/mapa-serwisu-e.php",
                     "http://www.kadoro.pl/mapa-serwisu-f.php",
                     "http://www.kadoro.pl/mapa-serwisu-g.php",
                     "http://www.kadoro.pl/mapa-serwisu-i.php",
                     "http://www.kadoro.pl/mapa-serwisu-j.php",
                     "http://www.kadoro.pl/mapa-serwisu-k.php",
                     "http://www.kadoro.pl/mapa-serwisu-l.php",
                     "http://www.kadoro.pl/mapa-serwisu-m.php",
                     "http://www.kadoro.pl/mapa-serwisu-n.php",
                     "http://www.kadoro.pl/mapa-serwisu-o.php",
                     "http://www.kadoro.pl/mapa-serwisu-p.php",
                     "http://www.kadoro.pl/mapa-serwisu-r.php",
                     "http://www.kadoro.pl/mapa-serwisu-s.php",
                     "http://www.kadoro.pl/mapa-serwisu-t.php",
                     "http://www.kadoro.pl/mapa-serwisu-u.php",
                     "http://www.kadoro.pl/mapa-serwisu-v.php",
                     "http://www.kadoro.pl/mapa-serwisu-w.php",
                     "http://www.kadoro.pl/mapa-serwisu-x.php",
                     "http://www.kadoro.pl/mapa-serwisu-y.php",
                     "http://www.kadoro.pl/mapa-serwisu-z.php",

                         
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
      

    
