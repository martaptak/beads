# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

from scrapy.item import Item, Field


class MartaItem(Item):
    name = Field()
    image = Field()
    url = Field()
    id = Field()
    category = Field()   
    subcategory = Field()
    type = Field()
    rozmiar = Field()
    kolor = Field()
    ksztalt = Field()

