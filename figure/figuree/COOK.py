import re
import pygame, sys, random, time

WINDOW_WIDTH = 640
WINDOW_HEIGHT = 480
BLACK = (0, 0, 0)

img = pygame.image.load('./Floor.jpeg')
killdic = {}
ingrelist = []
width = 20
height = 20
targetlist = []

def randpos_x():
	x = random.randint(width, WINDOW_WIDTH - width - 120)
	return x
def randpos_y():
	x = random.randint(height, WINDOW_HEIGHT - height)
	return x

def check(target):
	if target.name == 'applepie':
		if 'apple' in ingrelist and 'pie' in ingrelist:
			return True
	elif target.name == 'salad':
		if 'tomato' in ingrelist and 'spinach' in ingrelist:
			return True
	elif target.name == 'vegetablesandwitch':
		if 'breadbasket' in ingrelist and 'spinach' in ingrelist:
			return True
	elif target.name == 'cheeseeggandsandwitch':
		if 'breadbasket' in ingrelist and 'cheese' in ingrelist and 'egg' in ingrelist:
			return True

	return False

def collision(man, obj, target):
	if obj.name == 'recipe' or obj.name == 'scoreboard' or obj.name == 'target':
		return False
	if pygame.Rect.colliderect(man.rect, obj.rect):
		ingrelist.append(obj.name)
		killdic[obj] = True
		obj.kill()
		return check(target) 

def reset():
	killdic.clear()
	ingrelist.clear()
	targetlist.clear()


def main(score):
	pygame.init()
	window_surface = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
	my_font = pygame.font.SysFont(None, 30)
	pygame.display.set_caption('TEST')
	window_surface.blit(img, (0, 0))
	apple = ingredient('apple', './apple.png', width, height, randpos_x(), randpos_y())
	breadbasket = ingredient('breadbasket', './breadbasket.png', width, height, randpos_x(), randpos_y())
	cheese = ingredient('cheese', './cheese.png', width, height, randpos_x(), randpos_y())
	egg = ingredient('egg', './egg.png', width, height, randpos_x(), randpos_y())
	pie = ingredient('pie', './pie.png', width, height, randpos_x(), randpos_y())
	spinach = ingredient('spinach', './spinach.png', width, height,randpos_x(), randpos_y())
	tomato = ingredient('tomato', './tomato.png', width, height, randpos_x(), randpos_y())
	recipe = ingredient('recipe', './recipe.png', 120, 300, 640, 390)
	scoreboard = ingredient('scoreboard', './scoreboard.png', 120, 90, 640, 90)
	applepie = Target('applepie', './applepie.png', 120, 90, 640, 480)
	cheeseeggandsandwitch = Target('cheeseeggandsandwitch', './cheeseeggandsandwitch.png', 120, 90, 640, 480)
	salad = Target('salad', './salad.png', 120, 90, 640, 480)
	vegetablesandwitch = Target('vegetablesandwitch', './vegetablesandwitch.png', 120, 90, 640, 480)
	target = targetlist[random.randint(0, 3)]
	window_surface.blit(target.image, target.rect)
	man = ppl('./man.png', 30, 30, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2)
	for i in killdic.keys():
		window_surface.blit(i.image, i.rect)
	window_surface.blit(man.image, man.rect)
	text_surface = my_font.render('Step: {}'.format(score), True, (0, 0, 0))
	window_surface.blit(text_surface, (540, 20))
	main_clock = pygame.time.Clock()
	pygame.display.update()

	while True:
		for event in pygame.event.get():
			if event.type == pygame.QUIT:
				pygame.quit()
				sys.exit()
			elif event.type == pygame.KEYDOWN:
				if event.key == pygame.K_LEFT:
					man.moveleft()
				elif event.key == pygame.K_RIGHT:
					man.moveright()
				elif event.key == pygame.K_UP:
					man.moveup()
				else:
					man.movedown()
				score += 1
				for i in killdic.keys():
					flag = collision(man, i, target)
					if flag:
						reset()
						return score
				bottom, right = man.rect.bottom, man.rect.right
				man.kill()
				window_surface.fill(BLACK)
				window_surface.blit(img, (0, 0))
				window_surface.blit(target.image, target.rect)
				man = ppl('./man.png', 30, 30, right, bottom)
				for i in killdic.keys():
					if not killdic[i]:
						window_surface.blit(i.image, i.rect)
				text_surface = my_font.render('Step: {}'.format(score), True, (0, 0, 0))
				window_surface.blit(text_surface, (540, 20))
				window_surface.blit(man.image, man.rect)
		pygame.display.update()
		main_clock.tick(60)


class Target(pygame.sprite.Sprite):

	def __init__(self, string, path, width, height, random_x, random_y):
		super().__init__()
		self.raw_image = pygame.image.load(path).convert_alpha()
		self.image = pygame.transform.scale(self.raw_image, (width, height))
		self.rect = self.image.get_rect()
		self.rect.bottomright = (random_x, random_y)
		self.name = string
		targetlist.append(self)



class ingredient(pygame.sprite.Sprite):

	def __init__(self, string:str, path, width, height, random_x, random_y):
		super().__init__()
		self.raw_image = pygame.image.load(path).convert_alpha()
		self.image = pygame.transform.scale(self.raw_image, (width, height))
		self.rect = self.image.get_rect()
		self.rect.bottomright = (random_x, random_y)
		self.name = string
		killdic[self] = False

class ppl(pygame.sprite.Sprite):
	
	def __init__(self, path, width, height, random_x, random_y):
		super().__init__()
		self.raw_image = pygame.image.load(path).convert_alpha()
		self.image = pygame.transform.scale(self.raw_image, (width, height))
		self.rect = self.image.get_rect()
		self.rect.bottomright = (random_x, random_y)
		self.list = []

	def moveleft(self):
		self.rect.left -= 10
		if self.rect.left < 0:
			self.rect.left = 0

	def moveright(self):
		self.rect.right += 10
		if self.rect.right > WINDOW_WIDTH:
			self.rect.right = WINDOW_WIDTH
	
	def movedown(self):
		self.rect.bottom += 10
		if self.rect.bottom < 0:
			self.rect.bottom = 0
	
	def moveup(self):
		self.rect.top -= 10
		if self.rect.top > WINDOW_HEIGHT:
			self.rect.top = WINDOW_HEIGHT

if __name__ == '__main__':
	if len(sys.argv) == 2:
		count = int(sys.argv[1])
	else:
		count = 1
	score = 0
	while count:
		score = main(score)
		count -= 1
	print("Step : {}".format(score))