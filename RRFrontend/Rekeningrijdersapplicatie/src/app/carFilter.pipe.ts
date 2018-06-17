import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'carFilter'
})
export class CarFilterPipe implements PipeTransform {

  transform(items: any, filter: any, isAnd: boolean): any {
    if (filter && Array.isArray(items)) {
      let filterKeys = Object.keys(filter);
      return items.filter(item => {
        return filterKeys.some((keyName) => {
          console.log(filter);
          console.log(item);
          let found: boolean = false;

          found = this.filter(item.fueltype, filter.carFiltertje) &&
            this.filter(item.licensePlate + "", filter.carFiltertje) &&
            this.filter(item.tracker.ID + "", filter.carFiltertje) &&
            this.filter(item.weight + "", filter.carFiltertje)
          ;
          return !found;
        });
      });
    }
    else {
      return items;
    }
  }

  filter(property: string, filter: string): boolean {
    //returns false when the filter is not found in the value of the specified property
    return (filter !== undefined && property.toLowerCase().indexOf(filter.toLowerCase()) === -1);
  }

}
