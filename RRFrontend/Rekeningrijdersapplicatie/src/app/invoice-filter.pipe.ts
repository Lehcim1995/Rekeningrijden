import {Pipe, PipeTransform} from '@angular/core';
import {DatePipe} from "@angular/common";

@Pipe({
  name: 'invoiceFilter'
})
export class InvoiceFilterPipe implements PipeTransform {

  constructor(public datepipe: DatePipe) {
  }
  transform(items: any, filter: any, isAnd: boolean): any {
    if (filter && Array.isArray(items)) {
      let filterKeys = Object.keys(filter);
      return items.filter(item => {
        return filterKeys.some((keyName) => {
          console.log(filter);
          let found: boolean = false;

          found = this.filter(item.licenseplate, filter.invoiceFiltertje) &&
                          this.filter(item.date, filter.invoiceFiltertje) &&
                          this.filter(item.invoiceID, filter.invoiceFiltertje);
          return !found;
        });
      });
    }
    else {
      return items;
    }
  }

  filter(property: string, filter: string) : boolean {
    //returns false when the filter is not found in the value of the specified property
    return (filter !== undefined && property.toLowerCase().indexOf(filter.toLowerCase()) === -1);
  }
}
