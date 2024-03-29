// product shop list
export type Products = {
  id: number;
  image: string;
  name: string;
  description?: string;
  rating?: number;
  discount?: number;
  salePrice?: number;
  offerPrice?: number;
  gender?: string;
  categories?: string[];
  colors?: string[];
  popularity?: number;
  date?: number;
  created: Date;
  isStock?: boolean;
  new?: number;
  qty?: number;
};

// checkout-cart billing address
export type Address = {
  /* eslint-disable @typescript-eslint/no-explicit-any */
  id?: string | any | Date;
  name: string;
  destination: string;
  building: string;
  street: string;
  city: string;
  state: string;
  country: string;
  post: string;
  phone: string;
  isDefault: boolean;
};

// product reviews list
export type Reviews = {
  id: number;
  rating: number;
  review: string;
  date: Date | string;
  profile: {
    avatar: string;
    name: string;
    status: boolean;
  };
};

// product shop filter
export type ProductsFilter = {
  length?: number;
  search: string;
  sort: string;
  gender: string[];
  categories: string[];
  colors: string[];
  price: string;
  rating: number;
};

// product shop filter - sort options
export type SortOptionsProps = {
  value: string;
  label: string;
};

// product shop filter - colors options
export type ColorsOptionsProps = {
  label: string;
  value: string;
  bg: string;
};

export type PaymentOptionsProps = {
  id: number;
  value: string;
  title: string;
  caption: string;
  image: string;
  size: {
    width: number;
    height: number;
  };
};

export interface ProductStateProps {
  products?: Products[];
  cart?: Products[] | any;
  relatedProducts?: Products[];
  reviews?: Reviews[];
  addresses?: Address[];
  sortBy?: string;
  gender?: object | [] | string;
  category?: object | [] | string;
  price?: object | [] | string;
  error?: object | string | null;
  subTotal?: number;
  total?: number;
  discount?: number;
}
