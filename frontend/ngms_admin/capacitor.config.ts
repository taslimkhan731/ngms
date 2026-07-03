import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.odisha.ebhawanadmin',
  appName: 'e-Bhawan',
  webDir: 'www',
  bundledWebRuntime: false,
  server: {
    // url: 'http://203.193.144.93:9090/mnalco/nalco/',
    cleartext: true,
    androidScheme:'http'
  },
};

export default config;
