import { Component, Inject, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Filesystem, Directory } from '@capacitor/filesystem';
import { File } from '@ionic-native/file/ngx';
import { FileOpener } from '@ionic-native/file-opener/ngx';
import { ModalController, NavParams, Platform } from '@ionic/angular';

@Component({
  selector: 'app-doc-viewer',
  templateUrl: './doc-viewer.component.html',
  styleUrls: ['./doc-viewer.component.scss'],
})
export class DocViewerComponent implements OnInit {

  public data;
  public pdfUrl: SafeResourceUrl;

  constructor(
    private navparams: NavParams,
    private modalController: ModalController,
    private sanitizer: DomSanitizer,
    private platform: Platform,
    private opener: FileOpener,
    private file:File
  ) { }

  ngOnInit() { }

  ionViewWillEnter() {
    this.data = this.navparams.data.data;
    if (this.data.split(';')[0].split('/')[1] == 'pdf') {
      this.downloadAndDisplayPdf(this.data,'example.pdf')
      // this.openPDFInApp(this.data);
      // this.saveAndOpenPdf(this.data,'example.pdf');
    }
  }

  // openPDFInApp(base64String: string) {
  //   this.platform.ready().then(() => {
  //     // Convert Base64 string to data URI
  //     const dataUri = base64String;

  //     // Open the PDF in the InAppBrowser
  //     const browser = this.inAppBrowser.create(dataUri, '_blank', 'location=no');

  //     // Handle browser events as needed (e.g., close the browser when done)
  //     browser.on('exit').subscribe(() => {
  //       // Handle the exit event (e.g., back button pressed)
  //     });
  //   });
  // }

  async downloadAndDisplayPdf(base64Data: string, filename: string) {
    console.log("hdjhxjchjxc")
    const blob = this.base64ToBlob(base64Data, 'application/pdf');
    const dataUrl = await this.blobToDataUrl(blob);

    const directory = Directory.Documents; // Choose appropriate directory
    const result = await Filesystem.writeFile({
      path: `${directory}/${filename}`,
      data: dataUrl, // Use the data URL string
      recursive: true,
    });

    if (result) {
      const pdfFilePath = result.uri;
      this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(pdfFilePath);
    } else {
      console.log("dfjgdjjjjjjjjjjjj")
      // Handle error
    }
  }

  base64ToBlob(base64Data: string, contentType: string): Blob {
    const prefix = "data:application/pdf;base64,";
    base64Data =  base64Data.slice(prefix.length);
    const byteCharacters = atob(base64Data);
    const byteArrays = [];
    for (let offset = 0; offset < byteCharacters.length; offset += 512) {
      const slice = byteCharacters.slice(offset, offset + 512);
      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }
    return new Blob(byteArrays, { type: contentType });
  }

  async blobToDataUrl(blob: Blob): Promise<string> {
    return new Promise((resolve) => {
      const reader = new FileReader();
      reader.onload = (event) => {
        resolve(event.target.result as string);
      };
      reader.readAsDataURL(blob);
    });
  }


  public goBack() {
    this.modalController.dismiss();
  }

  convertBase64ToBlob(b64Data, contentType): Blob {
    contentType = contentType || '';
    const sliceSize = 512;
    b64Data = b64Data.replace(/^[^,]+,/, '');
    b64Data = b64Data.replace(/\s/g, '');
    const byteCharacters = window.atob(b64Data);
    const byteArrays = [];
    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
         const slice = byteCharacters.slice(offset, offset + sliceSize);
         const byteNumbers = new Array(slice.length);
         for (let i = 0; i < slice.length; i++) {
             byteNumbers[i] = slice.charCodeAt(i);
         }
         const byteArray = new Uint8Array(byteNumbers);
         byteArrays.push(byteArray);
    }
   return new Blob(byteArrays, {type: contentType});
 }
 saveAndOpenPdf(pdf: string, filename: string) {
  console.log("Inside saveAndOpenPdf")
  const writeDirectory = this.platform.is('ios') ? this.file.dataDirectory : this.file.externalDataDirectory;
  this.file.writeFile(writeDirectory, filename, this.convertBase64ToBlob(pdf, 'data:application/pdf;base64'), {replace: true})
    .then(() => {
        this.opener.open(writeDirectory + filename, 'application/pdf')
            .catch(() => {
                console.log('Error opening pdf file');
            });
    })
    .catch(() => {
        console.error('Error writing pdf file');
    });
}
}
