import mock from '../mockAdapter';

// third-party
import { add, sub } from 'date-fns';
import { Chance } from 'chance';
import product1 from '@/assets/images/e-commerce/prod-1.jpg';
import product2 from '@/assets/images/e-commerce/prod-2.jpg';
import product3 from '@/assets/images/e-commerce/prod-3.jpg';
import product4 from '@/assets/images/e-commerce/prod-4.jpg';
import product5 from '@/assets/images/e-commerce/prod-5.jpg';
import product6 from '@/assets/images/e-commerce/prod-6.jpg';
import product7 from '@/assets/images/e-commerce/prod-7.jpg';
import product8 from '@/assets/images/e-commerce/prod-8.jpg';

//Types
import type { Products } from '@/types/ecommerce/index';

const chance = new Chance();
// products list
const products: Products[] = [
  {
    id: 1,
    image: product1,
    name: chance.name(),
    description: chance.paragraph({ sentences: 2 }),
    rating: chance.floating({ min: 0.1, max: 5.0 }),
    discount: 25,
    salePrice: 350,
    offerPrice: 275,
    gender: 'male',
    categories: ['fashion', 'books'],
    colors: ['error', 'warning', 'primary', 'secondary'],
    popularity: chance.natural(),
    date: chance.natural(),
    created: sub(new Date(), { days: 8, hours: 6, minutes: 20 }),
    isStock: true,
    qty: 1
  },
  {
    id: 2,
    image: product2,
    name: chance.name(),
    description: chance.paragraph({ sentences: 2 }),
    rating: chance.floating({ min: 0.1, max: 5.0 }),
    discount: 10,
    salePrice: 89,
    offerPrice: 81,
    gender: 'kids',
    categories: ['fashion', 'toys'],
    colors: ['primary200', 'success', 'secondary200', 'warning'],
    popularity: chance.natural(),
    date: chance.natural(),
    created: sub(new Date(), { days: 10, hours: 8, minutes: 69 }),
    isStock: false,
    qty: 1
  },
  {
    id: 3,
    image: product3,
    name: chance.name(),
    description: chance.paragraph({ sentences: 2 }),
    rating: chance.floating({ min: 0.1, max: 5.0 }),
    discount: 40,
    salePrice: 85.0,
    offerPrice: 49.9,
    gender: 'male',
    categories: ['fashion', 'electronics'],
    colors: ['primary200', 'primary'],
    popularity: chance.natural(),
    date: chance.natural(),
    created: sub(new Date(), { days: 4, hours: 9, minutes: 50 }),
    isStock: true,
    qty: 1
  },
  {
    id: 4,
    image: product4,
    name: chance.name(),
    description: chance.paragraph({ sentences: 2 }),
    rating: chance.floating({ min: 0.1, max: 5.0 }),
    discount: 17,
    salePrice: 36.0,
    offerPrice: 29,
    gender: 'kids',
    categories: ['fashion', 'electronics', 'toys'],
    colors: ['error', 'warning', 'warning'],
    popularity: chance.natural(),
    date: chance.natural(),
    created: sub(new Date(), { days: 7, hours: 6, minutes: 45 }),
    isStock: false,
    qty: 1
  },
  {
    id: 5,
    image: product5,
    name: chance.name(),
    description: chance.paragraph({ sentences: 2 }),
    rating: chance.floating({ min: 0.1, max: 5.0 }),
    discount: 20,
    salePrice: 15,
    offerPrice: 12,
    gender: 'male',
    categories: ['books'],
    colors: ['warning', 'primary200'],
    popularity: chance.natural(),
    date: chance.natural(),
    created: sub(new Date(), { days: 2, hours: 9, minutes: 45 }),
    isStock: true,
    qty: 1
  },
  {
    id: 6,
    image: product6,
    name: chance.name(),
    description: chance.paragraph({ sentences: 2 }),
    rating: chance.floating({ min: 0.1, max: 5.0 }),
    discount: 13,
    salePrice: 99,
    offerPrice: 86,
    gender: 'female',
    categories: ['fashion', 'kitchen'],
    colors: ['primary', 'warning'],
    popularity: chance.natural(),
    date: chance.natural(),
    created: add(new Date(), { days: 6, hours: 10, minutes: 0 }),
    isStock: true,
    qty: 1
  },
  {
    id: 7,
    image: product7,
    name: chance.name(),
    description: chance.paragraph({ sentences: 2 }),
    rating: chance.floating({ min: 0.1, max: 5.0 }),
    discount: 15,
    salePrice: 16,
    offerPrice: 14.59,
    gender: 'male',
    categories: ['fashion'],
    colors: ['error', 'secondary', 'primary', 'warning'],
    popularity: chance.natural(),
    date: chance.natural(),
    created: add(new Date(), { days: 14, hours: 1, minutes: 55 }),
    isStock: false,
    qty: 1
  },
  {
    id: 8,
    image: product8,
    name: chance.name(),
    description: chance.paragraph({ sentences: 2 }),
    rating: chance.floating({ min: 0.1, max: 5.0 }),
    discount: 20,
    salePrice: 129,
    offerPrice: 100.0,
    gender: 'female',
    categories: ['toys'],
    colors: ['darkMain', 'primary', 'successDark'],
    popularity: chance.natural(),
    date: chance.natural(),
    created: sub(new Date(), { days: 0, hours: 11, minutes: 10 }),
    isStock: true,
    qty: 1
  }
];

// ==============================|| MOCK SERVICES ||============================== //

mock.onGet('/api/products/list').reply(() => {
  return [200, products];
});

export default products;
