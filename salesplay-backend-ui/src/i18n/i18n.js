import i18n from "i18next";
import LanguageDetector from 'i18next-browser-languagedetector';
import common_de from "../translations/de/de.json";
import common_en from "../translations/en/en.json";
import common_ja from "../translations/ja/ja.json";

const options = {
  interpolation: {
    escapeValue: false,
  },

  debug: true,

  resources: {
    en: {
      translations: common_en
    },
    de: {
      translations: common_de
    },
    ja: {
      translations: common_ja
    }
  },

  fallbackLng: 'en',

  ns: ['translations'],

  defaultNS: 'translations',

  react: {
    wait: false,
    bindI18n: 'languageChanged loaded',
    bindStore: 'added removed',
    nsMode: 'default'
  },
};

i18n
  .use(LanguageDetector)
  .init(options);

export default i18n;
